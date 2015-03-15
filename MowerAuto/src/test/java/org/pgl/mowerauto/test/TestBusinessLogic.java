package org.pgl.mowerauto.test;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pgl.mowerauto.Application;
import org.pgl.mowerauto.business.MowerManager;
import org.pgl.mowerauto.business.MowerManagerImpl;
import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DaoFactory;
import org.pgl.mowerauto.dao.test.DaoFactoryMock;
import org.pgl.mowerauto.dao.test.DataSourceMock;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.State;
import org.pgl.mowerauto.entity.test.MockMower;
import org.pgl.mowerauto.ui.UiManager;
import org.pgl.mowerauto.ui.UiMock;
import org.pgl.mowerauto.util.UtilsFormat;

/**
 * Unit tests to business logic.
 * */
public class TestBusinessLogic {

	private MowerManager mowerManager;

	public TestBusinessLogic() {
		//Use UiMock to display message
		new UiManager(UiMock.getInstance());

    	Locale defLocale = Locale.ENGLISH;
    	Locale.setDefault(defLocale);
		Application.bundle = ResourceBundle.getBundle("ResourceBundle", defLocale);
		
		this.mowerManager = new MowerManagerImpl();
		DaoFactory factory = new DaoFactoryMock();
		Dao mockDao = factory.getDao();
		this.mowerManager.setDao(mockDao);
	}

	@Before
	public void beforeTest(){
		UiMock.getInstance().clearMessages();
		MockMower.reinitNumber();
	}

	/**
	 * Case : a mower is moving and provides final state.
	 * */
	@Test
	public void testClassicalMovingMower(){
		DataSourceMock dataSource = new DataSourceMock(DataSourceMock.Type.NORMAL);
		
		this.mowerManager.loadOperation(dataSource);
		this.mowerManager.executeOperation();

		//Get the list of displayed messages
		List<String> displayedMessages = UiMock.getInstance().getDisplayedMessages();

		//With knowledge of operation we determined the expected final state :
		List<State> expectedStates = dataSource.providesFinalStates();
		Mower firstMower = new MockMower(expectedStates.get(0), 1);
		Mower secondMower = new MockMower(expectedStates.get(1), 2);

		String expectedLocationMsg1 = UtilsFormat.formatMsgFinalLocation(firstMower);
		String expectedLocationMsg2 = UtilsFormat.formatMsgFinalLocation(secondMower);

		//Check the expected messages have been displayed :
		Assert.assertTrue("The first mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg1));
		Assert.assertTrue("The second mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg2));
	}

	/**
	 * Case : a mower attempts to get out grass.
	 * */
	@Test
	public void testMoveOutGrass(){
		DataSourceMock dataSource = new DataSourceMock(DataSourceMock.Type.MOVE_OUT);
		
		this.mowerManager.loadOperation(dataSource);
		this.mowerManager.executeOperation();
		
		List<String> displayedMessages = UiMock.getInstance().getDisplayedMessages();
		
		//With knowledge of operation we determined the expected final state :
		List<State> expectedStates = dataSource.providesFinalStates();
		Mower mower = new MockMower(expectedStates.get(0), 1);
		
		String expectedLocationMsg = UtilsFormat.formatMsgFinalLocation(mower);
		String expectedMoveOutMsg = String.format(Application.bundle.getString("MSG_MOVE_OUT"), 1);
		
		//Check the expected messages have been displayed :
		Assert.assertTrue("The mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg));
		Assert.assertTrue("The mower has not communicated the failed attempt to move out.", 
				displayedMessages.contains(expectedMoveOutMsg));
	}

	/**
	 * Case : a mower attempts have an initial state out if grass.
	 * */
	@Test
	public void testInitialOutGrass(){
		DataSourceMock dataSource = new DataSourceMock(DataSourceMock.Type.INITIAL_OUT);
		
		this.mowerManager.loadOperation(dataSource);
		this.mowerManager.executeOperation();
		
		List<String> displayedMessages = UiMock.getInstance().getDisplayedMessages();
		
		String expectedInitialOutMsg = String.format(Application.bundle.getString("MSG_INITIAL_OUT"), 1);

		//Check the expected message has been displayed :
		Assert.assertTrue("The failed attempt to put out of grass a mower has not been pointed.", 
				displayedMessages.contains(expectedInitialOutMsg));
	}


	/**
	 * Case : a mower cross the location of previous mower.
	 * */
	@Test
	public void testMoveOccupiedCase(){
		DataSourceMock dataSource = new DataSourceMock(DataSourceMock.Type.MOVE_OCCUPIED);
		
		this.mowerManager.loadOperation(dataSource);
		this.mowerManager.executeOperation();

		//Get the list of displayed messages
		List<String> displayedMessages = UiMock.getInstance().getDisplayedMessages();

		//With knowledge of operation we determined the expected final state :
		List<State> expectedStates = dataSource.providesFinalStates();
		Mower firstMower = new MockMower(expectedStates.get(0), 1);
		Mower secondMower = new MockMower(expectedStates.get(1), 2);

		String expectedLocationMsg1 = UtilsFormat.formatMsgFinalLocation(firstMower);
		String expectedLocationMsg2 = UtilsFormat.formatMsgFinalLocation(secondMower);
		//Knowing that the second sequence is a case of move to occupied case.
		String expectedMoveOccupied = String.format(Application.bundle.getString("MSG_MOVE_OCCUPIED"), 2);
		
		//Check the expected messages have been displayed :
		Assert.assertTrue("The first mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg1));
		Assert.assertTrue("The second mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg2));
		Assert.assertTrue("The failed attempt to move a mower on occupied case has not been pointed.", 
				displayedMessages.contains(expectedMoveOccupied));
	}
	
	/**
	 * Case : two mowers have the same initial location.
	 * */
	@Test
	public void testInitialOccupied(){
		DataSourceMock dataSource = new DataSourceMock(DataSourceMock.Type.INITIAL_OCCUPIED);
		
		this.mowerManager.loadOperation(dataSource);
		this.mowerManager.executeOperation();

		//Get the list of displayed messages
		List<String> displayedMessages = UiMock.getInstance().getDisplayedMessages();

		//With knowledge of operation we determined the expected final state :
		List<State> expectedStates = dataSource.providesFinalStates();
		Mower firstMower = new MockMower(expectedStates.get(0), 1);
		//Normally the second mower is null because it is located on occupied case
		
		String expectedLocationMsg = UtilsFormat.formatMsgFinalLocation(firstMower);
		//Knowing that the second sequence is a case of initial location on occupied case.
		String expectedInitialOccupiedMsg = String.format(Application.bundle.getString("MSG_INITIAL_OCCUPIED"), 2);
		
		//Check the expected messages have been displayed :
		Assert.assertTrue("The first mower has not communicated its correct final state.", 
				displayedMessages.contains(expectedLocationMsg));
		Assert.assertTrue("The failed attempt to locate initially a mower on occupied case has not been pointed.", 
				displayedMessages.contains(expectedInitialOccupiedMsg));	
	}


}

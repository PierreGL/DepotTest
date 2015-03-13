package org.pgl.mowerauto.test;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DaoFactory;
import org.pgl.mowerauto.dao.DataSourceFile;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Orientation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;

/**
 * Unit tests to DaoFile implementation. 
 * */
public class TestDaoFileImpl {

	private Dao dao;

	public TestDaoFileImpl() {
		DaoFactory factory = new DaoFactory();
		dao = factory.getDao(Dao.Type.FILE);
	}

	/**
	 * Parse file to get a complete operation and check that the expected information are collected.
	 * */
	@Test
	public void testGetOperation(){
		URL url = getClass().getResource("/validFile.txt");
		String path = url.getPath();

		File file = new File(path);
		DataSourceFile sourceFile = new DataSourceFile(file);
		Operation op = dao.getOperation(sourceFile);

		Grass grass = op.getGrass();
		Assert.assertTrue("The length of grass collected is not correct", grass.getLength() == 5);
		Assert.assertTrue("The width of grass collected is not correct", grass.getWidth() == 5);

		List<Sequence> sequences = op.getSequences();
		Assert.assertTrue("The number of sequences collected is not correct", sequences.size() == 2);
		
		Sequence firstSequence = sequences.get(0);
		Mower mower1 = firstSequence.getMower();
		State state1 = mower1.getState();
		Assert.assertTrue("The abscissa of first mower collected is incorrect", state1.getPositionX() == 1); 
		Assert.assertTrue("The ordinate of first mower collected is incorrect", state1.getPositionY() == 2); 
		Assert.assertTrue("The orientation of first mower collected is incorrect", state1.getOrientation() == Orientation.NORTH); 

		List<Instruction> firstInstructions = firstSequence.getInstructions();
		Instruction[] expectedInstr = {Instruction.LEFT, Instruction.FORWARD, Instruction.LEFT, 
				Instruction.FORWARD, Instruction.LEFT, Instruction.FORWARD, Instruction.LEFT, 
				Instruction.FORWARD, Instruction.FORWARD};
		
		int i =0;
		for (Instruction instruction : firstInstructions) {
			Assert.assertTrue("An expected instruction has not been found.", instruction.equals(expectedInstr[i])); 
			i++;
		}
	}

	/**
	 * Test Case : Parse a file whith incorrect header : erased type.
	 * */
	@Test
	public void testIncorrectHeaderType(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileHeaderType.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong type in header file has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file whith incorrect header : wrong number of data.
	 * */
	@Test
	public void testIncorrectHeaderNumber(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileHeaderNumber.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong number of data in header file has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file whith incorrect header : wrong structure.
	 * */
	@Test
	public void testIncorrectHeaderStructure(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileHeaderStructure.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong structure in header file has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file with problem : a state line has an incorrect number of data.
	 * */
	@Test
	public void testIncorrectStateNumber(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileStateNumber.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong number of data in a state line has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file with problem : a state line has a data with incorrect type.
	 * */
	@Test
	public void testIncorrectStateType(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileStateType.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong type of data in a state line has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file with problem : a state line has a impossible orientation.
	 * */
	@Test
	public void testIncorrectStateOrient(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileStateOrient.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("A wrong orientation in a state line has not been pointed.", exceptionFound);
	}




	/**
	 * Test Case : Parse a file with problem : a mower has not been instructions.
	 * */
	@Test
	public void testIncorrectInstructionsMissing(){
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileInstructionsMissing.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("The absence of instructions of a mower in file has not been pointed.", exceptionFound);
	}

	/**
	 * Test Case : Parse a file with problem : an instructions line has a data with wrong type.
	 * */
	@Test
	public void testIncorrectInstructionType(){        
		boolean exceptionFound = false;

		try {
			this.attemptGetOperation("/invalidFileInstructionsType.txt");
		} catch (Exception e) {
			exceptionFound = true;
		}

		Assert.assertTrue("The absence of instructions of a mower in file has not been pointed.", exceptionFound);        
	}

	/**
	 * Attempt get operation with dao on a defined file.
	 * 
	 * @param fileName The file to parse with DAO.
	 * */
	private void attemptGetOperation(String fileName){
		URL url = getClass().getResource(fileName);
		String path = url.getPath();

		File file = new File(path);
		DataSourceFile sourceFile = new DataSourceFile(file);
		dao.getOperation(sourceFile);
	}

}

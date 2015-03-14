package org.pgl.mowerauto.entity.test;

import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.State;

/**
 * This class allows to create a Mower with chosen id, to perform some tests.
 * */
public class MockMower extends Mower {

	/**
	 * The mower has chosen id.
	 * */
	public MockMower(State finalState, int id){
		super(finalState);
		super.id = id;
	}	
	
	/**
	 * Reinit number of mock to tests.
	 * */
	public static void reinitNumber(){
		nbMower=0;
	}
}

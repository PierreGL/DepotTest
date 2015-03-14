package org.pgl.mowerauto.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Orientation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;
import org.pgl.mowerauto.util.exception.UnknownDataMockTypeException;

/**
 * An emulated data source allowing to test business couch with hard-coded values.
 * */
public class DataSourceMock implements DataSource {

	private Type mockType;

	public DataSourceMock(Type mockType){
		this.mockType = mockType;
	}

	/**
	 * Provide an operation object according to a mockType.
	 * */
	public Operation provideOperation() {
		Operation result;

		switch (mockType) {
		case NORMAL:
			result = createNormalOperation();
			break;
		case MOVE_OCCUPIED:
			result = createMoveOccupiedOperation();
			break;
		case INITIAL_OCCUPIED:
			result = createInitialOccupiedOperation();
			break;
		case MOVE_OUT:
			result = createMoveOutOperation();
			break;
		case INITIAL_OUT:
			result = createInitialOutOperation();
			break;
		default:
			throw new UnknownDataMockTypeException();
		}

		return result;
	}
	
	/**
	 * Provides the ordered list of mower final state, 
	 * will be established after execution of matching data mock.
	 * 
	 * If a mower moving is impossible, its state is replaced by null.
	 * 
	 * @return List of final state in the same order that mower executed.
	 * */
	public List<State> providesFinalStates(){
		List<State> result = new ArrayList<>();

		switch (mockType) {
		case NORMAL:
			State firstMowerExpectedState = new State(Orientation.WEST, 0, 0);
			State secondMowerExpectedState = new State(Orientation.EAST, 6, 3);
			result.add(firstMowerExpectedState);
			result.add(secondMowerExpectedState);
			break;
		case MOVE_OCCUPIED:
			firstMowerExpectedState = new State(Orientation.NORTH, 4, 5);
			secondMowerExpectedState = new State(Orientation.SOUTH, 3, 3);
			result.add(firstMowerExpectedState);
			result.add(secondMowerExpectedState);			
			break;
		case INITIAL_OCCUPIED:
			firstMowerExpectedState = new State(Orientation.NORTH, 4, 5);
			secondMowerExpectedState = null;//Value null because initially overlap the first mower.
			result.add(firstMowerExpectedState);
			result.add(secondMowerExpectedState);				
			break;
		case MOVE_OUT:
			firstMowerExpectedState = new State(Orientation.NORTH, 4, 4);
			result.add(firstMowerExpectedState);
			break;
		case INITIAL_OUT:
			firstMowerExpectedState = null;//Value null because initially out.
			secondMowerExpectedState =  new State(Orientation.WEST, 0, 3);
			result.add(firstMowerExpectedState);
			result.add(secondMowerExpectedState);
			break;
		default:
			throw new UnknownDataMockTypeException();
		}
		
		return result;
	}

	private Operation createNormalOperation(){
		Grass grass = new Grass(7, 7);
		Operation newOp = new Operation(grass);

		State state1 = new State(Orientation.WEST, 2, 2);
		Mower newMower1 = new Mower(state1);
		List<Instruction> instructions1 = new ArrayList<>();
		instructions1.add(Instruction.FORWARD);
		instructions1.add(Instruction.LEFT);
		instructions1.add(Instruction.FORWARD);
		instructions1.add(Instruction.FORWARD);
		instructions1.add(Instruction.RIGHT);
		instructions1.add(Instruction.FORWARD);

		Sequence newSeq1 = new Sequence(newMower1, instructions1);
		newOp.addSequence(newSeq1);
		//The theorical final state is 0 0 West

		State state2 = new State(Orientation.SOUTH, 6, 6);
		Mower newMower2 = new Mower(state2);
		List<Instruction> instructions2 = new ArrayList<>();
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.RIGHT);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.LEFT);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.LEFT);
		instructions2.add(Instruction.FORWARD);

		Sequence newSeq2 = new Sequence(newMower2, instructions2);
		newOp.addSequence(newSeq2);
		//The theorical final state is 6 3 EAST

		return newOp;
	}

	private Operation createInitialOccupiedOperation(){
		Grass grass = new Grass(6, 6);
		Operation op = new Operation(grass);

		State initialState = new State(Orientation.NORTH, 3, 3);
		Mower newMower = new Mower(initialState);
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);

		Sequence newSeq = new Sequence(newMower, instructions);
		op.addSequence(newSeq);//Terminated 4 5 N

		State initialState2 = new State(Orientation.EAST, 3, 3);//Initial state has same location that first mower, cannot be put on grass.
		Mower newMower2 = new Mower(initialState2);

		List<Instruction> instructions2 = new ArrayList<>();
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.RIGHT);
		instructions2.add(Instruction.FORWARD);

		Sequence newSeq2 = new Sequence(newMower2, instructions2);
		op.addSequence(newSeq2);

		return op;	
	}

	private Operation createMoveOccupiedOperation(){
		Grass grass = new Grass(6, 6);
		Operation op = new Operation(grass);

		State initialState = new State(Orientation.NORTH, 3, 3);
		Mower newMower = new Mower(initialState);
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);

		Sequence newSeq = new Sequence(newMower, instructions);
		op.addSequence(newSeq);//Terminated 4 5 N

		//This second sequence will go to case final state of the first 
		State initialState2 = new State(Orientation.EAST, 2, 5);
		Mower newMower2 = new Mower(initialState2);
		List<Instruction> instructions2 = new ArrayList<>();
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.FORWARD);//At second forward should go to case 4 5 but can't
		instructions2.add(Instruction.RIGHT);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.FORWARD);

		Sequence newSeq2 = new Sequence(newMower2, instructions2);
		op.addSequence(newSeq2); //Cause the cross finish at 3 3 S

		return op;
	}

	private Operation createMoveOutOperation(){

		Grass grass = new Grass(4, 4);
		Operation op = new Operation(grass);

		State initialState = new State(Orientation.NORTH, 3, 3);
		Mower newMower = new Mower(initialState);
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.FORWARD);//This second forward bring out themower
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);
		//Cause the move out the final state is : 4 4 N

		Sequence newSeq = new Sequence(newMower, instructions);
		op.addSequence(newSeq);

		return op;
	}

	private Operation createInitialOutOperation(){
		Grass grass = new Grass(6, 6);
		Operation op = new Operation(grass);

		State initialState = new State(Orientation.SOUTH, 8, 5);
		Mower newMower = new Mower(initialState);
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);

		Sequence newSeq = new Sequence(newMower, instructions);
		op.addSequence(newSeq);

		//This second sequence run normally
		State initialState2 = new State(Orientation.EAST, 2, 5);
		Mower newMower2 = new Mower(initialState2);
		List<Instruction> instructions2 = new ArrayList<>();
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.RIGHT);
		instructions2.add(Instruction.FORWARD);
		instructions2.add(Instruction.FORWARD);

		Sequence newSeq2 = new Sequence(newMower2, instructions2);
		op.addSequence(newSeq2); //Finish at 0 3 W
		
		return op;
	}

	/**
	 * Different type of data source mock defining an expected behavior.
	 * */
	public enum Type{
		/**
		 * Normal case.
		 * */
		NORMAL,

		/**
		 * Case a mower go to case occupied by another mower. 
		 * */
		MOVE_OCCUPIED,

		/**
		 * Case two mower have the same initial location.
		 * */
		INITIAL_OCCUPIED,

		/**
		 * Case a mower go out of grass.
		 * */
		MOVE_OUT,

		/**
		 * Case a mower has initial location out.
		 * */
		INITIAL_OUT;
	}
}

package org.pgl.mowerauto.dao;

import java.util.ArrayList;
import java.util.List;

import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Orientation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;

public class DataSourceMock implements Dao {

	@Override
	public Operation getOperation(DataSource source) {

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
		
		return newOp;
	}

}

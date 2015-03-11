package org.pgl.mowerauto.entity;

import java.util.List;

/**
 * This class represents a sequence. A sequence is a list of ordered instruction give to a mower. 
 * */
public class Sequence {
    
    private Mower mower;
    
    private List<Instruction> instructions;
    
    public Sequence(Mower mower, List<Instruction> instructions) {
		super();
		this.mower = mower;
		this.instructions = instructions;
	}

	public Mower getMower() {
        return mower;
    }
    
    public List<Instruction> getInstructions() {
        return instructions;
    }

}

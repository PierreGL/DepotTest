package org.pgl.mowerauto.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an operation. An operation is a set of ordered sequences performed on a grass.
 * */
public class Operation {

    /**
     * The grass concerned by sequence of operations.
     * */
    private Grass grass;
    
    /**
     * THe ordered sequences.
     * */
    private List<Sequence> sequences;
    
    public Operation(Grass grass){
        this.grass = grass;
        this.sequences = new ArrayList<>();
    }
    
    public void addSequence(Sequence sequence){
        this.sequences.add(sequence);
    }
    
    public Grass getGrass() {
        return grass;
    }
    
    public List<Sequence> getSequences() {
        return sequences;
    }

//    /**
//     * LIst of Mower to activate in order.
//     * */
//    private List<Mower> orderedMowerList;
//
//    /**
//     * The list of mower and associated instruction.
//     * */
//    private Map<Mower, List<Instruction>> mowerInstructions;
//    
//    public Operation(Grass grass){
//        this.grass = grass;
//    }
//    
//    /**
//     * Add new mower in sequence of operations with list of instructions after the mower already recorded.
//     * */
//    public void addMower(Mower mower, List<Instruction> instructions){
//        orderedMowerList.add(mower);
//        mowerInstructions.put(mower, instructions);
//    }
//    
//    /**
//     * Add new mower in sequence of operations with list of instructions and defined order.  
//     * */
//    public void addMower(Mower mower, List<Instruction> instructions, int order){
//        orderedMowerList.add(order, mower);
//        //TODO verifier que order OK (demarre a 0???)
//        mowerInstructions.put(mower, instructions);
//    }

}

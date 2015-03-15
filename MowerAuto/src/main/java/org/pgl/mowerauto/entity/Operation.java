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
     * The ordered sequences.
     * */
    private List<Sequence> sequences;
    
    public Operation(){
    }
    
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

}

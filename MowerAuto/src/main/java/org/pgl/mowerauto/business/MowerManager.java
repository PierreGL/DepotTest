package org.pgl.mowerauto.business;

import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Sequence;

/**
 * Interface defining the business logic concerning the mowers.
 * */
public interface MowerManager {
    
    Operation loadOperation(DataSource source);
    
    void executeOperation(Operation sequence);
    
    void executeSequence(Grass grass, Sequence sequence);
    
    void displayState(Mower mower);

}

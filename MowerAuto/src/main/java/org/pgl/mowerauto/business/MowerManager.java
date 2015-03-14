package org.pgl.mowerauto.business;

import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Sequence;

/**
 * Interface defining the business logic concerning the mowers.
 * */
public interface MowerManager {
    
	/**
	 * Load an operation from a datasource.
	 * */
    void loadOperation(DataSource source);
    
    /**
     * Execute the last loaded operation.
     * 
     * @exception IllegalStateException if execute is called before loadOperation.
     * */
    void executeOperation();
    
	/**
	 * Execute a sequence and when finish, returns the Mower with updated state.
	 * 
	 * @param grass The grass.
	 * @param The sequence concerned.
	 * @return The mower updated.
	 * */
    Mower executeSequence(Grass grass, Sequence sequence);
    
    /**
     * To display to user the final state of a mower.
     * 
     * @param mower The defined mower.
     * */
    void displayFinalState(Mower mower);
    
    /**
     * To set a new dao.
     * */
    void setDao(Dao dao);

}

package org.pgl.mowerauto.business;

import org.pgl.mowerauto.dao.DataSource;

/**
 * Interface defining the business logic concerning the mowers.
 * */
public interface MowerManager {
    
	/**
	 * Load an operation from a datasource.
	 * */
    void loadOperation(DataSource source);
    
    /**
     * Execute the loaded operation.
     * 
     * @exception IllegalStateException if execute is called before loadOperation.
     * */
    void execute();

}

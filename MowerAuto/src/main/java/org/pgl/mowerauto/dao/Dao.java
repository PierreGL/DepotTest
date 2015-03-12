package org.pgl.mowerauto.dao;

import org.pgl.mowerauto.entity.Operation;

/**
 * The DAO (Data access object) manage access to data.
 * */
public interface Dao {

    /**
     * To load a defined dataSource.
     * 
     * @param source The defined dataSource to load.
     * @exception IncorrectDataSourceException If a source not matching is provided.
     * */
    void loadDataSource(DataSource source);
    
	/**
	 * Get an operation from the data source loaded.
	 * 
	 * @return Operation provides by data source.
	 * */
    Operation getOperation();
}

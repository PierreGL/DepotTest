package org.pgl.mowerauto.dao;

import org.pgl.mowerauto.entity.Operation;

/**
 * The DAO (Data access object) manage access to data.
 * */
public interface Dao {
	
	/**
	 * Get an operation from the data source.
	 * 
	 * @param source The defined source.
	 * @return Operation provides by data source.
	 * */
    Operation getOperation(DataSource source);
    
    public enum Type{
    	FILE,
    	MOCK;
    }
}

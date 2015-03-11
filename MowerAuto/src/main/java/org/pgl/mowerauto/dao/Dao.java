package org.pgl.mowerauto.dao;

import org.pgl.mowerauto.entity.Operation;

/**
 * The DAO (Data access object) manage access to data.
 * */
public interface Dao {

	/**
	 * Get an operation from a data source.
	 * 
	 * @param source A data source object.
	 * @return Operation provides by data source.
	 * */
    Operation getOperation(DataSource source);
}

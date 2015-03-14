package org.pgl.mowerauto.dao;

/**
 * Provides DAO.
 * */
public interface DaoFactory {
	
	/**
	 * Get an instance of DAO adapted to a type of source.
	 * */
	public Dao getDao();
}

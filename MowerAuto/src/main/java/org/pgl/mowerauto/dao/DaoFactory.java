package org.pgl.mowerauto.dao;

/**
 * Provides DAO.
 * */
public class DaoFactory {
	
	/**
	 * Get an instance of DAO adapted to a type of source.
	 * */
	public Dao getDao(Dao.Type type){
		if(Dao.Type.FILE.equals(type)){
			return new DaoFileImpl();
		}else{
			
		}
		
		return null;
	}

}

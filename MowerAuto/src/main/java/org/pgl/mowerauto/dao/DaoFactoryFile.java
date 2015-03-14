package org.pgl.mowerauto.dao;

public class DaoFactoryFile implements DaoFactory {

	@Override
	public Dao getDao() {
		return new DaoFileImpl();
	}

}

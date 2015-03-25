package org.pgl.mowerauto.dao.test;

import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DaoFactory;

public class DaoFactoryMock implements DaoFactory{

    @Override
    public Dao getDao() {
        return new DaoMockImpl();
    }

}

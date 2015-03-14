package org.pgl.mowerauto.dao.test;

import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.util.exception.IncorrectDataSourceException;

public class DaoMockImpl implements Dao {

	public DaoMockImpl() {
	}
	
	@Override
	public Operation getOperation(DataSource source) {
        if(!(source instanceof DataSourceMock)){
            throw new IncorrectDataSourceException("An incorrect DataSource has been passed to DAO.");
        }
        
        DataSourceMock sourceFile = (DataSourceMock)source;		
                
        return sourceFile.provideOperation();
	}

}

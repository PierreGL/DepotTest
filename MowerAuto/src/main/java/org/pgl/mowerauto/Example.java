package org.pgl.mowerauto;

import org.pgl.mowerauto.business.OperationManager;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Operation;

public class Example {

    public void example(){
        OperationManager operationManager = null;
        
        DataSource source = null;
        
        Operation op = operationManager.loadOperation(source);
        
        operationManager.executeOperation(op);
    }
}

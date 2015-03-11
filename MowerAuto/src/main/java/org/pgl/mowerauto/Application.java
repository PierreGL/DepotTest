package org.pgl.mowerauto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.pgl.mowerauto.business.OperationManager;
import org.pgl.mowerauto.business.OperationManagerImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.dao.DataSourceFile;
import org.pgl.mowerauto.entity.Operation;

/**
 * The entry point of application.
 * */
public class Application {

    public static void main(String[] args) {

    	OperationManager operationManager = new OperationManagerImpl();

    	
        //If none argument entered, the graphical user interface occurs.
        if(args == null){
            //TODO petite appli graphique
        
        //If argumentS entered, they are treated as data file path to execute. 
        }else{
        	
        	List<Operation> listOperationEntered = new ArrayList<>();
        	
        	for (String arg : args) {
        		File file = new File(arg);
        		//TODO checker validiter du format du file
				DataSource source = new DataSourceFile(file);
	        	Operation operation = operationManager.loadOperation(source);
	        	listOperationEntered.add(operation);
			}
        	
        	for (Operation operation : listOperationEntered) {
				operationManager.executeOperation(operation);//Ne pas oublier la sortie : info mower
			}
        	
            //TODO Charger fichier correspondant a args[0] ...
        }
    }

}

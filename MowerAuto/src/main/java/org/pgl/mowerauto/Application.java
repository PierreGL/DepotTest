package org.pgl.mowerauto;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.pgl.mowerauto.business.MowerManager;
import org.pgl.mowerauto.business.MowerManagerImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.dao.DataSourceFile;

/**
 * The entry point of application.
 * */
public class Application {
	
	public static ResourceBundle bundle;

    public static void main(String[] args) {
    	initConfig();
    	launch(args);
    }
    
    /**
     * Initialize configuration of application.
     * */
    public static void initConfig(){
    	Locale defLocale = Locale.FRANCE;
    	Locale.setDefault(defLocale);
		bundle = ResourceBundle.getBundle("ResourceBundle", defLocale);
    }
    
    /**
     * Launch the application.
     * */
    public static void launch(String[] args){
    	MowerManager mowerManager = new MowerManagerImpl();

    	
        //If none argument entered, the graphical user interface occurs.
        if(args == null){
            //TODO petite appli graphique
        
        //If argumentS entered, they are treated as data file path to execute. 
        }else{	
        	for (String arg : args) {
        		File file = new File(arg);
				DataSource source = new DataSourceFile(file);
	        	mowerManager.loadOperation(source);
				mowerManager.execute();//Ne pas oublier la sortie : info mower
			}
        }
    }

}

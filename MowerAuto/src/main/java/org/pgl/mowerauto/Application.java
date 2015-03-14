package org.pgl.mowerauto;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.pgl.mowerauto.business.MowerManager;
import org.pgl.mowerauto.business.MowerManagerImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.dao.DataSourceFile;
import org.pgl.mowerauto.ui.UiConsole;
import org.pgl.mowerauto.ui.UiManager;
import org.pgl.mowerauto.util.exception.FunctionnalException;

/**
 * The entry point of application.
 * */
public class Application {
	
	public static ResourceBundle bundle;
	
    public static void main(String[] args) {
    	new Application(args);
    }
    
    public Application(String[] args){
    	try {
        	init();
        	launch(args);
		} catch (FunctionnalException e) {
			UiManager.display(e.getMessage());
		}
    }
    
    /**
     * Initialize application.
     * */
    private void init(){
    	initConfig();
    }
    
    /**
     * Initialize configuration and bundle.
     * */
    private void initConfig(){
    	//Init bundle
    	Locale defLocale = Locale.FRANCE;
    	Locale.setDefault(defLocale);
		bundle = ResourceBundle.getBundle("ResourceBundle", defLocale);
    }
    
    private void initConsole(){
    	new UiManager(UiConsole.getInstance());
    }
        
    /**
     * Launch the application.
     * */
    private void launch(String[] args){
    	MowerManager mowerManager = new MowerManagerImpl();
    	
        //If none argument entered, the graphical user interface occurs.
        if(args.length == 0){
            //TODO petite appli graphique
        
        //If argumentS entered, they are treated as data file path to execute. 
        }else{	
        	initConsole();
        	
        	for (String arg : args) {
        		File file = new File(arg);
				DataSource source = new DataSourceFile(file);
	        	mowerManager.loadOperation(source);
				mowerManager.executeOperation();//Ne pas oublier la sortie : info mower
			}
        }
    }

}

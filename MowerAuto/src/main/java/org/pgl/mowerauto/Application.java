package org.pgl.mowerauto;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.business.MowerManager;
import org.pgl.mowerauto.business.MowerManagerImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.dao.DataSourceFile;
import org.pgl.mowerauto.ui.UiConsole;
import org.pgl.mowerauto.ui.UiManager;
import org.pgl.mowerauto.util.Bundle;
import org.pgl.mowerauto.util.exception.FunctionnalException;

/**
 * The entry point of application.
 * */
public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    private Bundle bundle;

    private UiManager uiManager;

    public Application(){
        init();
    }

    public static void main(String[] args) {
        Application appli = new Application();
        appli.launch(args);
    }

    /**
     * Initialize application.
     * */
    private void init(){
        this.bundle = Bundle.getInstance();
        initConsole();
        LOGGER.info("MowerAuto initialized");
    }

    /**
     * Initialize user console.
     * */
    private void initConsole(){
        this.uiManager = UiManager.getInstance();
        this.uiManager.setOutput(UiConsole.getInstance());
    }

    /**
     * Launch the application.
     * */
    private void launch(String[] args){
        MowerManager mowerManager = new MowerManagerImpl();

        //If none argument entered, a message is displayed.
        if(args.length > 0){
            String path = args[0];
            try {
                File file = new File(path);
                DataSource source = new DataSourceFile(file);
                mowerManager.loadOperation(source);
                mowerManager.executeOperation();
            } catch (FunctionnalException fe) {
                LOGGER.error(fe);
                this.uiManager.display(fe.getMessage());
            }
        }else {
            this.uiManager.display(this.bundle.get("MSG_NO_PATH"));
        }

        String entered = this.uiManager.displayAndWait(this.bundle.get("MSG_ENTER_PATH"));
        if(!"q".equalsIgnoreCase(entered)){//If the user enter Q, the program is terminating, else relaunch the program on entered string. 
            launch(new String[]{entered});
        }
    }

}

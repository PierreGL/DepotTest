package org.pgl.mowerauto.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * To manager the user interface. 
 * */
public class UiManager {

    private static final Logger LOGGER = LogManager.getLogger(UiManager.class);

    private UiOuput output;

    private static UiManager instance;

    private UiManager(){
    }

    public static synchronized UiManager getInstance(){
        if(instance == null){
            instance = new UiManager();
        }
        return instance;
    }

    /**
     * Displays a message to user.
     * 
     * @param msg The message.
     * */
    public void display(String msg){
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Display : \""+msg+"\"");
        }		

        output.display(msg);
    }

    /**
     * Displays a message to user and wait response.
     * 
     * @param msg The message.
     * @return The response from user.
     * */
    public String displayAndWait(String msg){
        LOGGER.info("Display : \""+msg+"\"");
        LOGGER.info("wait...");
        String response = output.displayAndWait(msg);
        LOGGER.info("The user entered : \""+response+"\"");
        return response;
    }

    public void setOutput(UiOuput output) {
        this.output = output;
    }
}

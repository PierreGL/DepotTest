package org.pgl.mowerauto.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.Application;

/**
 * To manager the user interface. 
 * */
public class UiManager {

	private static Logger logger = LogManager.getLogger(UiManager.class);

	private static UiOuput out;

	public UiManager(UiOuput output){
		out = output;
	}

	/**
	 * Displays a message to user.
	 * 
	 * @param msg The message.
	 * */
	public static void display(String msg){
		if(logger.isDebugEnabled()){
			logger.debug("Display : \""+msg+"\"");
		}		

		out.display(msg);
	}

	/**
	 * Displays a message to user and wait response.
	 * 
	 * @param msg The message.
	 * @return The response from user.
	 * */
	public static String displayAndWait(String msg){
		logger.info("Display : \""+msg+"\"");
		logger.info("wait...");

		String response = out.displayAndWait(msg);
		if("q".equalsIgnoreCase(response)){
			display(Application.bundle.getString("MSG_QUIT"));
			System.exit(0);
		}

		logger.info("The user entered : \""+response+"\"");
		return response;
	}

}

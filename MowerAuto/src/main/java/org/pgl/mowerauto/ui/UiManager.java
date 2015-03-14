package org.pgl.mowerauto.ui;

/**
 * To manager the user interface. 
 * */
public class UiManager {
	
	private static UiOuput out;
	
	public UiManager(UiOuput output){
		out = output;
	}
	
	public static void display(String msg){
		out.display(msg);
	}
	
	public static String displayAndWait(String msg){
		return out.displayAndWait(msg);
	}

}

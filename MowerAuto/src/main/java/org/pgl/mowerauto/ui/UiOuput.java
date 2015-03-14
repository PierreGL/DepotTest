package org.pgl.mowerauto.ui;

public interface UiOuput {
	/**
	 * Display a message to user.
	 * */
	void display(String msg);
	
	/**
	 * Display a message to user and wait response.
	 * */
	String displayAndWait(String msg);
}

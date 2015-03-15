package org.pgl.mowerauto.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * The specific UI console cannot be displayed during test, this UiMock allows to replace standard console.
 * */
public class UiMock implements UiOuput {
	private List<String> displayedMessages;

	private static UiMock instance;
		
	public static synchronized UiMock getInstance(){
		if(instance == null){
			instance = new UiMock();
		}
		return instance;
	}
	
	private UiMock(){
		displayedMessages = new ArrayList<String>();
	}
	
	@Override
	public void display(String msg) {
		displayedMessages.add(msg);
	}

	@Override
	public String displayAndWait(String msg) {
		return msg;
	}
	
	/**
	 * Clear the list of stored messages.
	 * */
	public void clearMessages(){
		this.displayedMessages.clear();
	}
	
	/**
	 * Get the list of stored messages.
	 * */
	public List<String> getDisplayedMessages() {
		return displayedMessages;
	}

}

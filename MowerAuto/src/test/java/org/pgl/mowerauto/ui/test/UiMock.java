package org.pgl.mowerauto.ui.test;

import java.util.ArrayList;
import java.util.List;

import org.pgl.mowerauto.ui.UiOuput;

/**
 * The specific UI console cannot be displayed during test, then it is replaced by simple System.out.
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
		System.out.println(msg);
	}

	@Override
	public String displayAndWait(String msg) {
		System.out.println(msg);
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

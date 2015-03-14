package org.pgl.mowerauto.ui;

import java.io.Console;
import java.io.PrintWriter;

/**
 * The user interface type console.
 * */
public class UiConsole implements UiOuput {

	private Console console;
	private PrintWriter writer;
	
	private static UiConsole instance;
	
	public static synchronized UiConsole getInstance(){
		if(instance == null){
			instance = new UiConsole();
		}
		return instance;
	}
	
	private UiConsole(){
		this.console = System.console();
		//If there already console used, return null.
		if(this.console == null){
			System.exit(0);
		}
		this.writer = this.console.writer();
	}
	
	@Override
	public void display(String msg){
		this.writer.append(msg+"\n");
		this.writer.flush();
	}
	
	@Override
	public String displayAndWait(String msg){
		this.writer.append(msg+"\n");
		this.writer.flush();
		String responseUser= console.readLine();
		return responseUser;
	}
	

}

package org.pgl.mowerauto.ui;

import java.io.Console;
import java.io.PrintWriter;

import org.pgl.mowerauto.util.exception.ConsoleUsedException;

/**
 * The user interface type console.
 * */
public class UiConsole implements UiOuput {

    private Console console;
    private PrintWriter writer;

    private static UiConsole instance;

    private UiConsole(){
        this.console = System. console();

        //If there already console used, return null.
        if(this.console == null){
            throw new ConsoleUsedException("The Console system is already used.");
        }
        this.writer = this.console.writer();
    }

    public static synchronized UiConsole getInstance(){
        if(instance == null){
            instance = new UiConsole();
        }
        return instance;
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
        return console.readLine();
    }


}

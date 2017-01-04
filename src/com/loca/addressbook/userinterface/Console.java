package com.loca.addressbook.userinterface;

import java.util.Scanner;

public class Console implements ConsolePrinter {

	private InputHandler inputHandler;
	private boolean isQuit = false;
 
    public void registerInputHandler(InputHandler inputHandler) {
    	this.inputHandler = inputHandler;
    }
 
    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        CommandLineFactory commandLineFactory = new CommandLineFactory();
		while (!isQuit) {
			String userInput = scanner.nextLine();
			CommandLine commandLine = commandLineFactory.parse(userInput);
            inputHandler.handle(commandLine);
		}
		scanner.close();
	}

    @Override
    public void print(String output) {
        System.out.println(output);
    }

    public void close() {
        isQuit = true;
    }

}

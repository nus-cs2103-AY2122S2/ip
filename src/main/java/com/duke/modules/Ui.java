package com.duke.modules;

import com.duke.command.CommandResult;

/**
 * A class responsible for dealing with user interactions.
 */
public class Ui {
    public static final String BOT_NAME = "LUMU";
    public static final String START_MESSAGE = String.format("Hello I'm\n%s\nWhat can I do for you?", BOT_NAME);
    public static final String LINE_BREAK = "----------------------------------------";

    public Ui() {
    }

    /**
     * Gets user input and passes it to a Parser to be read.
     * Prints out the given string in the CommandResult object given by the Parser object.
     */
    public String initialize(String userInput, TaskList taskList) {
        Parser parser = new Parser(taskList);
        String commands = userInput.trim();
        CommandResult cmdResult = parser.parse(commands);
        return showMessage(cmdResult.getResultMessage());
    }

    private String showMessage(String message) {
        String outputMessage = String.format("%s", message);
        return outputMessage;
    }
}

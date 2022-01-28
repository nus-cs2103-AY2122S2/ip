package com.duke.modules;

import com.duke.command.CommandResult;

import java.util.Scanner;

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
    public void initialize(TaskList taskList) {
        showStartMessage();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(taskList);
        while (scanner.hasNextLine()) {
            String commands = scanner.nextLine().trim();
            CommandResult cmdResult = parser.parse(commands);
            showMessage(cmdResult.getResultMessage());
            if (cmdResult.isShutdown()) break;
        }
    }

    private void showStartMessage() {
        String outputMessage = String.format("%s\n%s\n%s", LINE_BREAK, START_MESSAGE, LINE_BREAK);
        System.out.println(outputMessage);
    }

    private void showMessage(String message) {
        String outputMessage = String.format("%s\n%s", message, LINE_BREAK);
        System.out.println(outputMessage);
    }
}

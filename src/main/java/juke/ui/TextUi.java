package juke.ui;

import java.util.Scanner;

import juke.command.Command;
import juke.command.CommandHandler;
import juke.command.Result;
import juke.exception.JukeInvalidCommandException;

/**
 * Manages the user interface involving user input and console output.
 */
public class TextUi {
    private static final String LINE_PREFIX = "> ";
    private static final String LINE_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String LINE_INDENT = "    ";
    private static final String LOGO = "                                                             _____   \n"
            + "          _____  ______   _____     ______   _______    _____\\    \\  \n"
            + "         |\\    \\_\\     \\  \\    \\   |\\     \\  \\      \\  /    / |    | \n"
            + "         \\ \\     \\\\    |  |    |    \\\\     \\  |     /|/    /  /___/| \n"
            + "          \\|      ||   |  |    |     \\|     |/     //|    |__ |___|/ \n"
            + "           |      ||    \\_/   /|      |     |_____// |       \\       \n"
            + "   ______  |      ||\\         \\|      |     |\\     \\ |     __/ __    \n"
            + "  /     / /      /|| \\         \\__   /     /|\\|     ||\\    \\  /  \\   \n"
            + " |      |/______/ | \\ \\_____/\\    \\ /_____/ |/_____/|| \\____\\/    |  \n"
            + " |\\_____\\      | /   \\ |    |/___/||     | / |    | || |    |____/|  \n"
            + " | |     |_____|/     \\|____|   | ||_____|/  |____|/  \\|____|   | |  \n"
            + "  \\|_____|                  |___|/                          |___|/   \n";
    private static final String GREET_MESSAGE = "Greetings Executor!";

    private Scanner in;

    /**
     * Constructor that initializes the scanner for user input.
     */
    public TextUi() {
        this.in = new Scanner(System.in);
    }

    /**
     * Handles the logic of running the main UI loop for Juke CLI.
     */
    public void runUiLoop() {
        printPrefix();
        try {
            Command cmd = CommandHandler.fetchCommand(getInput());
            CommandHandler.execute(cmd);
            displayResult(CommandHandler.fetchResult(cmd));
        } catch (JukeInvalidCommandException e) {
            formattedPrint(e.getMessage());
        }
    }

    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void greet() {
        printLogo();
        formattedPrint(GREET_MESSAGE);
    }

    /**
     * Prints the Juke logo.
     */
    public void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints the line prefix.
     */
    public void printPrefix() {
        System.out.print(LINE_PREFIX);
    }

    /**
     * Prints the message associated with a success, or the error message otherwise.
     *
     * @param result Result of a command execution.
     */
    public void displayResult(Result result) {
        try {
            String[] string = result.getOrThrow();
            formattedPrint(string);
        } catch (Exception e) {
            formattedPrint(e.getMessage());
        }
    }

    /**
     * Prints a string with decorative formatting.
     *
     * @param texts Strings to print.
     */
    public void formattedPrint(String... texts) {
        System.out.println(LINE_DIVIDER);
        for (String str : texts) {
            System.out.println(LINE_INDENT + str);
        }
        System.out.println(LINE_DIVIDER + System.lineSeparator());
    }
}

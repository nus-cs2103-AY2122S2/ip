package juke.common;

import juke.command.Command;
import juke.command.CommandHandler;
import juke.command.Result;
import juke.exception.JukeInvalidCommandException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Manages the user interface involving user input and console output.
 */
public class Ui {
    private static final String LINE_PREFIX = "\u232c ";
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
    
    private Scanner in;
    
    /**
     * Constructor that initializes the scanner for user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }
    
    public void runUiLoop() {
        this.printPrefix();
        try {
            Command cmd = this.getCommand(this.getInput());
            if (cmd != null) {
                this.executeAndPrint(cmd);
            }
        } catch (JukeInvalidCommandException e) {
            this.formattedPrint(e.getMessage());
        }
    }
    
    public String getInput() {
        return this.in.nextLine();
    }
    
    public Command getCommand(String input) throws JukeInvalidCommandException {
        ArrayList<String[]> paramSplit = Parser.parseInput(input);
        if (paramSplit.get(0)[0].isBlank()) {
            return null;
        }
        Supplier<Command> cmdSup = CommandHandler.COMMANDS.get(paramSplit.get(0)[0]);
        if (cmdSup == null) {
            throw new JukeInvalidCommandException(paramSplit.get(0)[0]);
        }
        Command cmd = cmdSup.get().addParameter("", paramSplit.remove(0)[1]);
        for (String[] args : paramSplit) {
            cmd = cmd.addParameter(args[0], args[1]);
        }
        return cmd;
    }
    
    public void executeAndPrint(Command command) {
        command.execute();
        this.displayResult(command.getResult());
    }
    
    /**
     * Prints the welcome message.
     */
    public void greet() {
        this.printLogo();
        this.formattedPrint("Greetings Executor!");
    }
    
    /**
     * Prints the Juke logo.
     */
    public void printLogo() {
        System.out.println(LOGO);
    }
    
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
            this.formattedPrint(string);
        } catch (Exception e) {
            this.formattedPrint(e.getMessage());
        }
    }
    
    /**
     * Prints a string with decorative formatting.
     *
     * @param text String to print.
     */
    public void formattedPrint(String... text) {
        System.out.println(LINE_DIVIDER);
        for (String str : text) {
            System.out.println(LINE_INDENT + str);
        }
        System.out.println(LINE_DIVIDER + System.lineSeparator());
    }
}

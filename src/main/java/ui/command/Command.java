package ui.command;

import task.*;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * An abstract class to represent the commands
 * that can be issued to a ChatBot.
 */
public abstract class Command {
    /**
     * Constants for styling of printing command feedback
     */
    private final static int DELIMITER_LENGTH = 25;
    private final static int INDENT_SIZE = 4;
    private final static String DELIMITER = "_".repeat(DELIMITER_LENGTH);
    private final static String INDENT = " ".repeat(INDENT_SIZE);

    /**
     * Execute current command in ChatBot context
     *
     * @return whether the command is a terminating command
     */
    public abstract boolean execute();

    /**
     * Name of current command
     */
    private final String name;

    /**
     * String representation of arguments for command
     */
    private final String args;

    protected Command(String name, String args) {
        this.name = name;
        this.args = args;
    }

    /**
     * Parse user inputted command to extract
     * the name and args of the command, returning
     * the concrete Command subclass for the input
     *
     * @param input Command inputted by user
     * @param tasks Collection of tasks maintained by ChatBot
     * @return Command object corresponding to input
     */
    public static Command parseCommand(String input, ArrayList<Task> tasks) {
        String name = input;
        String args = null;
        // Separate command name and args
        if (input.contains(" ")) {
            String[] command = input.split(" ", 2);
            name = command[0];
            args = command[1];
        }

        Command command;
        switch (name) {
            case "list":
                command = new ListCommand(name, args, tasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                command = new AddTaskCommand(name, args, tasks);
                break;
            case "mark":
                command = new MarkTaskCommand(name, args, tasks);
                break;
            case "unmark":
                command = new UnmarkTaskCommand(name, args, tasks);
                break;
            case "bye":
                command = new ExitCommand(name, args);
                break;
            default:
                return null;
        }
        return command;
    }

    /**
     * Print response of executed command styled between 2 lines
     * with indentation. Each item in response array is printed
     * in a new line
     *
     * @param response list of response to print
     */
    protected static void styledPrint(ArrayList<String> response) {
        response.add(0, DELIMITER);
        response.add(DELIMITER);
        // Prepend each response item with an indent and a line break
        String styledResponse = response.stream()
                .map(item -> INDENT + item + '\n')
                .reduce("", (prevLine, nextLine) -> prevLine + nextLine);
        System.out.println(styledResponse);
    }

    /**
     * Reconstruct original command by user
     *
     * @return String representing original command
     */
    protected String getOriginalCommand() {
        if (this.args != null) {
            return this.name + " " + this.args;
        }
        return this.name;
    }

    protected String getName() {
        return this.name;
    }

    protected String getArgs() {
        return this.args;
    }
}

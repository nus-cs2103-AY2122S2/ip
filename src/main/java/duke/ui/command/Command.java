package duke.ui.command;

import duke.data.TaskList;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * An abstract class to represent the commands
 * that can be issued to a ChatBot.
 */
public abstract class Command {
    /** Constants for styling of printing command feedback */
    private static final int FEEDBACK_DELIMITER_LENGTH = 25;
    private static final int FEEDBACK_INDENT_SIZE = 4;
    private static final String FEEDBACK_DELIMITER = "_".repeat(FEEDBACK_DELIMITER_LENGTH);
    private static final String FEEDBACK_INDENT = " ".repeat(FEEDBACK_INDENT_SIZE);

    /** Name of current command */
    private final String name;

    /** String representation of arguments for command */
    private final String args;

    protected Command(String name, String args) {
        this.name = name;
        this.args = args;
    }

    /**
     * Execute current command in ChatBot context.
     *
     * @return Whether the command is a terminating command.
     * @throws IllegalArgumentException If the command is executed
     * with invalid args.
     */
    public abstract boolean execute() throws IllegalArgumentException;

    /**
     * Parses user inputted command to extract
     * the name and args of the command, returning
     * the concrete Command subclass for the input.
     *
     * @param input Command inputted by user.
     * @param taskList TaskList maintained by ChatBot.
     * @return Command object corresponding to input.
     * @throws IllegalArgumentException If the command is not valid.
     */
    public static Command parseCommand(String input, TaskList taskList) throws IllegalArgumentException {
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
            command = new ShowTaskListCommand(name, args, taskList);
            break;
        case "todo":
        case "deadline":
        case "event":
            // Fallthrough for add task commands
            command = new AddTaskCommand(name, args, taskList);
            break;
        case "delete":
            command = new DeleteTaskCommand(name, args, taskList);
            break;
        case "mark":
            command = new MarkTaskCommand(name, args, taskList);
            break;
        case "unmark":
            command = new UnmarkTaskCommand(name, args, taskList);
            break;
        case "find":
            command = new FindCommand(name, args, taskList);
            break;
        case "bye":
            command = new ExitCommand(name, args);
            break;
        default:
            throw new IllegalArgumentException("Invalid command");
        }
        return command;
    }

    /**
     * Print response of executed command styled between 2 lines
     * with indentation. Each item in response array is printed
     * in a new line.
     *
     * @param response List of response to print.
     */
    public static void styledPrint(ArrayList<String> response) {
        response.add(0, FEEDBACK_DELIMITER);
        response.add(FEEDBACK_DELIMITER);
        // Prepend each response item with an indent and a line break
        String styledResponse = response.stream()
                .map(item -> FEEDBACK_INDENT + item + '\n')
                .reduce("", (prevLine, nextLine) -> prevLine + nextLine);
        System.out.println(styledResponse);
    }

    /**
     * Reconstruct original command by user.
     *
     * @return String representing original command.
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

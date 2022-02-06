package duke.chatbot.command;

import java.util.ArrayList;

import duke.data.TaskList;

/**
 * @author Jiaaa-yang
 *
 * An abstract class to represent the commands
 * that can be issued to a ChatBot.
 */
public abstract class Command {

    /** Name of current command */
    private final String name;

    /** String representation of arguments for command */
    private final String args;

    protected Command(String name, String args) {
        this.name = name;
        this.args = args;
    }

    /**
     * Executes current command in ChatBot context and
     * returns the response to the command ra.
     *
     * @return ArrayList of string containing feedback to command.
     * @throws IllegalArgumentException If the command is executed
     * with invalid args.
     */
    public abstract ArrayList<String> execute() throws IllegalArgumentException;

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
            command = new MarkTaskCommand(name, args, MarkTaskCommand.MarkType.DONE, taskList);
            break;
        case "unmark":
            command = new MarkTaskCommand(name, args, MarkTaskCommand.MarkType.UNDONE, taskList);
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

    protected String getName() {
        return this.name;
    }

    protected String getArgs() {
        return this.args;
    }
}

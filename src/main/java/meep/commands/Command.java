package meep.commands;

import meep.task.ListTask;

/**
 * Represents an executable command.
 */
public class Command {

    public static final String COMMAND_WORD = "command";

    /**
     * Constructor for class Command.
     */
    public Command() {
    }

    /**
     * Executes method should be implemented by child classes.
     *
     * @param tasks the task list.
     * @return Exception that the method not supposed to be used.
     */
    public String execute(ListTask tasks) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


    /**
     * Returns command word.
     *
     * @return command word.
     */
    public String toString() {
        return COMMAND_WORD;
    }


}

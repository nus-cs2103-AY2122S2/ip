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
     * Execute method should be implemented by child classes.
     *
     * @param tasks the task list.
     * @return None
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

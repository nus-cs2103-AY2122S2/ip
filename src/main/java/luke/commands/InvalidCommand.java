package luke.commands;

import luke.data.TaskList;

/**
 * Implements the invalid command
 */
public class InvalidCommand extends Command {

    private static final String DEFAULT_MESSAGE = "Oops, the force does not support this action.\nPlease try again :(";
    private static final String ERROR_MESSAGE = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
    private String error;
    private boolean isError;

    /**
     * Constructs the invalid command without triggering the error message.
     */
    public InvalidCommand() {
        isError = false;
    }

    /**
     * Constructs the invalid command with the error message, displaying the output as an error.
     * @param error The error message.
     */
    public InvalidCommand(String error) {
        this.error = error;
        isError = true;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        if (isError) {
            return new CommandResult(String.format(ERROR_MESSAGE, error));
        }
        return new CommandResult(DEFAULT_MESSAGE);
    }
}

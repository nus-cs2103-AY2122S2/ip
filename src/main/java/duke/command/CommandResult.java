package duke.command;

/**
 * A class representing the result of a Command.
 * Stores the resulting message and a boolean marks if the task list has been modified or not.
 */
public class CommandResult {
    private String message = null;
    private boolean isModified = false;
    private boolean isError = false;

    public CommandResult() {

    }

    public CommandResult(String message) {
        this.message = message;
    }

    /**
     * Alternative Constructor for CommandResult.
     *
     * @param message Resulting message after executing the command.
     * @param isModified Boolean to show if the task list has been modified or not.
     */
    public CommandResult(String message, boolean isModified) {
        this.message = message;
        this.isModified = isModified;
    }

    /**
     * Alternative Constructor for an error message.
     * @param message Resulting message after executing the command.
     * @param isModified Boolean to show if the task list has been modified or not.
     * @param isError Boolean to show if the result is an error.
     */
    public CommandResult(String message, boolean isModified, boolean isError) {
        this.message = message;
        this.isModified = isModified;
        this.isError = isError;
    }

    @Override
    public String toString() {
        if (message == null) {
            return "";
        }
        return message;
    }

    public boolean isModified() {
        return isModified;
    }

    public boolean isError() {
        return isError;
    }
}

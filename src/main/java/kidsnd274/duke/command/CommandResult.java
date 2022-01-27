package kidsnd274.duke.command;

/**
 * A class representing the result of a Command
 *
 * Stores the resulting message and a boolean marks if the task list has been modified or not
 */
public class CommandResult {
    private String message = null;
    private boolean isModified = false;

    public CommandResult() {

    }

    public CommandResult(String message) {
        this.message = message;
    }

    public CommandResult(String message, boolean isModified) {
        this.message = message;
        this.isModified = isModified;
    }

    @Override
    public String toString() {
        if (message == null) {
            return "";
        }
        return message;
    }

    public boolean isModified() {
        return this.isModified;
    }
}

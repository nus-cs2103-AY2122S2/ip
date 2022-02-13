package duke.exception;

/**
 * RonException type: Load
 * Exception thrown when error occurs while loading backup file on start
 */
public class LoadException extends RonException {
    public static final String MESSAGE = "No current tasks found, creating new task list.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}

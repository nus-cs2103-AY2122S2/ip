package duke.exception;

/**
 * RonException type: Duplicate task
 * Exception thrown when user inputs a task that already exist
 * in task list
 */
public class DuplicateException extends RonException {
    public static final String MESSAGE = "The task already exists.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}

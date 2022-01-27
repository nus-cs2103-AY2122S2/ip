package duke.exception;

/**
 * RonException type: Empty description
 * Exception thrown when user does not input a description of task
 */

public class EmptyDescriptionException extends RonException {
    public static final String message = "Please add a description for ";
    public static final String message2 = " task.";

    public String type;

    public EmptyDescriptionException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + message + type + message2;
    }
}

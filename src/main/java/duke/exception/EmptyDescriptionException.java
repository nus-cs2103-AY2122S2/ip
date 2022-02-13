package duke.exception;

/**
 * RonException type: Empty description
 * Exception thrown when user does not input a description of task
 */
public class EmptyDescriptionException extends RonException {
    public static final String MESSAGE_FRONT = "Please add a description for ";
    public static final String MESSAGE_END = " task.";

    private String type;

    public EmptyDescriptionException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + MESSAGE_FRONT + type + MESSAGE_END;
    }
}

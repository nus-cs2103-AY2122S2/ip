package duke.exception;

/**
 * RonException type: Delete index
 * Exception thrown when user does not input an index to delete task
 */
public class DeleteIndexException extends RonException {
    public static final String MESSAGE = "Please add index to delete.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}

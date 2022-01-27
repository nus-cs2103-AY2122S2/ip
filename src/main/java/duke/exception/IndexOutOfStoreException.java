package duke.exception;

/**
 * RonException type: Index out of store
 * Exception thrown when user inputs an index out of bounds of task list
 */

public class IndexOutOfStoreException extends RonException {
    public static final String message = "Task index not found.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}

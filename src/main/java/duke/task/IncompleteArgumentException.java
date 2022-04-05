package duke.task;

/**
 * Represents an exception caused by attempting to create a <code>Task</code> object with incomplete
 * arguments. A <code>IncompleteArgumentException</code> is represented by an error message.
 */
public class IncompleteArgumentException extends IllegalArgumentException {
    /**
     * Returns a new instance of <code>IncompleteArgumentException</code> with the specified Exception message.
     * @param message The error message of the exception.
     */
    public IncompleteArgumentException(String message) {
        super(message);
    }
}

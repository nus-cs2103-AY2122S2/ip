package duke.data.exception;

/**
 * An exception for the Duke class.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new exception for the class duke.
     *
     * @param errorMessage error message to be passed to the exception object
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

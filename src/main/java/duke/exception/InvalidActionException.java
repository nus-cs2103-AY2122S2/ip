package duke.exception;

/**
 * Exception for when the given command leads to an invalid action.
 */
public class InvalidActionException extends RuntimeException {

    /**
     * Creates a new instance of the exception.
     *
     * @param message The error message to be shown.
     */
    public InvalidActionException(String message) {
        super(message);
    }
}

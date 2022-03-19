package duke.exception;

/**
 * Exception for when the input is shorter than expected.
 */
public class IncompleteInputException extends Exception {

    /**
     * Creates a new instance of the exception.
     *
     * @param message the error message to be shown.
     */
    public IncompleteInputException(String message) {
        super(message);
    }
}

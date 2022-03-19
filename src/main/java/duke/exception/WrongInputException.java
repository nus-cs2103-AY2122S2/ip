package duke.exception;

/**
 * Exception for when the input is invalid.
 */
public class WrongInputException extends Exception {

    /**
     * Creates a new instance of the exception.
     *
     * @param message the error message to be shown.
     */
    public WrongInputException(String message) {
        super(message);
    }
}

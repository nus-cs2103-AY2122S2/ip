package seedu.duke.exceptions;

/**
 * Thrown when errors occur in the different classes.
 * Allows us to pinpoint what went wrong with specific error messages
 */
public class DukeException extends Exception {

    /**
     * Creates an exception.
     * @param message that details what went wrong
     */
    public DukeException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
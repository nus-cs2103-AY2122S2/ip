package seedu.exception;

/**
 * Inherits from the Java Exception class.
 * Creates DukeException object when error is caught to print out error message.
 */
public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
}

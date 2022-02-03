package seedu.duke;

/**
 * Inherits from the Java Exception class.
 * Creates DukeException object when error is caught to print out error message.
 */
public class DukeException extends Exception {

    DukeException(String error) {
        super(error);
    }
}
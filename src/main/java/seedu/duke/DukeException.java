package seedu.duke;

/**
 * Manages all user exceptions that the programmer expects the user to make
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

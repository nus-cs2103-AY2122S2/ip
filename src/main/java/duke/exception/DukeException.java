package duke.exception;

/**
 * Represents an exception related to the Duke application.
 */
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

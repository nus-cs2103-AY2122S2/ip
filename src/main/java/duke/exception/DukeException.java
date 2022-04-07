package duke.exception;

/**
 * Represents an exception that can be thrown by the Duke Application.
 */
public class DukeException extends java.lang.Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

package duke;

/**
 * Represents an error that can possibly occur during program's runtime.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(String message) {
        super(message);
    }
}

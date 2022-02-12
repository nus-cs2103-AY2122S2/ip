package duke.exception;

/**
 * Represents an application-specific error that may be raised during runtime.
 */
public abstract class DukeException extends Exception {
    /**
     * Creates a <code>DukeException</code> object.
     *
     * @param message Error-specific message.
     */
    public DukeException(String message) {
        super(message);
    }
}

package duke.exception;

/**
 * Represents an application-specific error that may be raised during runtime.
 */
public abstract class DukeException extends Exception {
    /**
     * Sole constructor for the <code>DukeException</code> class.
     * @param message Error-specific message.
     */
    public DukeException(String message) {
        super(message);
    }
}

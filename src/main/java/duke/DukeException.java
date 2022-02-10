package duke;

/**
 * Exception class catered for the Duke app.
 */
public class DukeException extends Exception {
    /**
     * Returns an exception that encapsulates the error message.
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}

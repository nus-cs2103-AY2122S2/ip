package duke.exception;

/**
 * Encapsulates an exception that occurs in the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Initialises a DukeException instance
     * with the specified message.
     *
     * @param message the detail message to be retrieved by getMessage().
     */
    public DukeException(String message) {
        super(message);
    }
}

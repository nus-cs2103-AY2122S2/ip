package duke.exception;

/**
 * Represents an Exception that could be throw by Duke.
 */
public class DukeException extends Exception {

    /**
     * A constructor to create a DukeException together with a message.
     * @param message The exception message that is to be shown.
     */
    public DukeException(String message) {
        super(message);
    }
}

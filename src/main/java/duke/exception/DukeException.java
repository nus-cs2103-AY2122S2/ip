package duke.exception;

/**
 * Exception class that are in relation to this bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for this exception class.
     *
     * @param message to be thrown when error is detected
     */
    public DukeException(String message) {
        super(message);
    }
}

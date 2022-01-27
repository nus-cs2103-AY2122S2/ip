package duke.exception;

/**
 * Exceptions raised by features of Duke chatbot extends this class.
 */
public class DukeException extends Exception {
    /**
     * Instantiates DukeException using the parent constructor from Exception.
     *
     * @param message String Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}

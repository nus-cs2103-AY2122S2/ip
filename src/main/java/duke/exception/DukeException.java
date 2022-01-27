package duke.exception;

/**
 * Handles Duke exceptions.
 */
public class DukeException extends Exception {

    /**
     * Prints the exception message.
     * @param message Message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }
}

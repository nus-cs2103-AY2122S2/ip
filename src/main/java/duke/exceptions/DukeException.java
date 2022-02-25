package duke.exceptions;

/**
 * Custom exception class to handle Duke-specific cases
 */
public class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception.
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Duke exception.
     */
    public DukeException() {
        super();
    }
}
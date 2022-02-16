package duke.exception;

/**
 * A custom exception to manage exceptions faced during runtime of Duke
 */
public class DukeException extends Exception {
    /**
     * Initializes a new Duke Exception with the given error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

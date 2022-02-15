package duke.exception;

/**
 * Handles Duke exceptions.
 */
public class DukeException extends Exception {

    /**
     * Prints the exception message.
     *
     * @param message Message to be printed.
     */
    public DukeException(String message) {
        super(message);
        assert message != null : "DukeException message cannot be null.";
        assert message.length() > 0 : "DukeException message cannot have an empty body.";
    }
}

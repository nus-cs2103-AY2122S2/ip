package duke.exception;

/**
 * Represents an error related to the parsing of a user input.
 */
public class DukeIllegalArgumentException extends DukeException {
    /**
     * Creates a <code>DukeIllegalArgumentException</code> object.
     *
     * @param message Error-specific message.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}

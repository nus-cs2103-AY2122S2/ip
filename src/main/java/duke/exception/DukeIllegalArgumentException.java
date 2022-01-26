package duke.exception;

/**
 * Represents an error related to the parsing of a user input.
 */
public class DukeIllegalArgumentException extends DukeException {
    /**
     * Sole constructor for the <code>DukeIllegalArgumentException</code> class.
     * @param message Error-specific message.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}

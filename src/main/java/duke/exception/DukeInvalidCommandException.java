package duke.exception;

/**
 * Represents an error related to the invocation of an invalid command.
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Sole constructor for the <code>DukeInvalidCommandException</code> class.
     * @param message Error-specific message.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}

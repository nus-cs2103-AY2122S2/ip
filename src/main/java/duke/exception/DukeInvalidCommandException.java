package duke.exception;

/**
 * Represents an error related to the invocation of an invalid command.
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Creates a <code>DukeInvalidCommandException</code> object.
     *
     * @param message Error-specific message.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}

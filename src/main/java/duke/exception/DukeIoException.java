package duke.exception;

/**
 * Represents an error related to a read or write operation on some Input/Output stream.
 */
public class DukeIoException extends DukeException {
    /**
     * Creates a <code>DukeIoException</code> object.
     *
     * @param message Error-specific message.
     */
    public DukeIoException(String message) {
        super(message);
    }
}

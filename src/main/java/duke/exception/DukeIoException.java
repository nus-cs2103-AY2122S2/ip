package duke.exception;

/**
 * Represents an error related to a read or write operation on some Input/Output stream.
 */
public class DukeIoException extends DukeException {
    /**
     * Sole constructor for the <code>DukeIoException</code> class.
     * @param message Error-specific message.
     */
    public DukeIoException(String message) {
        super(message);
    }
}

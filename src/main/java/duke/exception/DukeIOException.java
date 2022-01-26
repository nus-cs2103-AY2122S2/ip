package duke.exception;

/**
 * Represents an error related to a read or write operation on some Input/Output stream.
 */
public class DukeIOException extends DukeException {
    /**
     * Sole constructor for the <code>DukeIOException</code> class.
     * @param message Error-specific message.
     */
    public DukeIOException(String message) {
        super(message);
    }
}

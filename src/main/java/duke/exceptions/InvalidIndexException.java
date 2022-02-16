package duke.exceptions;

/**
 * Represents an exception which is thrown upon an invalid index input.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructs an InvalidIndexException.
     */
    public InvalidIndexException() {
        super("OOPS!!! This is an invalid index.");
    }
}

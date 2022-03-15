package duke.exceptions;

/**
 * Exception thrown when index is out of bounds.
 */
public class IndexException extends DukeException {
    public IndexException() {
        super("The provided index is invalid!");
    }
}

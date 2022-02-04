package duke.exception;

/**
 * Exception for invalid index.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor for InvalidIndexException.
     * @param indexRange the String of valid index range 1 to max_index.
     */
    public InvalidIndexException(String indexRange) {
        super("Invalid Index. Current task list range is " + indexRange);
    }
}

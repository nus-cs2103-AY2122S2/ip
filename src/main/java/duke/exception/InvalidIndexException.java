package duke.exception;

/**
 * Exception for invalid index.
 */
public class InvalidIndexException extends DukeException {

    public InvalidIndexException(String indexRange) {
        super("Invalid Index. Current task list range is " + indexRange);
    }
}

package duke.exception;

/**
 * Represents an exception thrown when Deadline task does not have date.
 */
public class EmptyByException extends DukeException {
    public EmptyByException(String errorMessage) {
        super(errorMessage);
    }
}

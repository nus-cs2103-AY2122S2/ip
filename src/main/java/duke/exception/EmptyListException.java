package duke.exception;

/**
 * Represents an exception thrown when an empty list is requested.
 */
public class EmptyListException extends DukeException {
    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}

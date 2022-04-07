package duke.exception;

/**
 * Represents an exception thrown when Event task does not have date.
 */
public class EmptyEventAtException extends DukeException {
    public EmptyEventAtException(String errorMessage) {
        super(errorMessage);
    }
}

package dazz.exception;

/**
 * Represents a <code>DazzException</code> that captures
 * empty <code>Task</code> description by the user.
 */
public class EmptyDescriptionException extends DazzException {

    /**
     * Constructs an <code>EmptyDescriptionException</code>.
     */
    public EmptyDescriptionException() {
        super(ErrorType.EMPTY_DESCRIPTION.getErrorMessage());
    }
}

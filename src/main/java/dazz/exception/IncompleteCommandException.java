package dazz.exception;

/**
 * Represents a <code>DazzException</code> that captures
 * an incomplete command.
 */
public class IncompleteCommandException extends DazzException {

    /**
     * Constructs an <code>IncompleteCommandException</code>.
     */
    public IncompleteCommandException() {
        super(ErrorType.INCOMPLETE_COMMAND.getErrorMessage());
    }
}

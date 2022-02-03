package dazz.exception;

/**
 * Represents a <code>DazzException</code> that captures
 * empty date specified by the user.
 */
public class EmptyDateException extends DazzException {

    /**
     * Constructs an <code>EmptyDateException</code>
     */
    public EmptyDateException() {
        super(ErrorType.EMPTY_DATE.getErrorMessage());
    }
}

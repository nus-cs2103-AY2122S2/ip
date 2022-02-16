package dazz.exception;

/**
 * Represents a <code>DazzException</code> that captures
 * invalid date format for <code>Deadline</code> and <code>Event</code>.
 */
public class InvalidDateFormatException extends DazzException {

    /**
     * Constructs an <code>InvalidDateFormatException</code>.
     */
    public InvalidDateFormatException() {
        super(ErrorType.INVALID_DATE.getErrorMessage());
    }
}

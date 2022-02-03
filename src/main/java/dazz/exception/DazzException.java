package dazz.exception;

/**
 * Represents the exception that is specific to Dazz.
 */
public class DazzException extends Exception {
    /**
     * Constructs a <code>DazzException</code> based on the error message.
     * @param error The error message.
     */
    public DazzException (String error) {
        super(error);
    }
}

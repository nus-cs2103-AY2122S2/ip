package sana.exception;

/**
 * This class represents the sana.exception thrown by Sana when she receives
 * an unknown command
 */
public class UnknownCommandException extends SanaException {
    /** The message Sana says when she receives an unknown command */
    private static final String MESSAGE = "Oy! I don't know what you're saying!";

    public UnknownCommandException() {
        super(UnknownCommandException.MESSAGE);
    }

    /**
     * The message Sana says when she receives an unknown command
     *
     * @return  message
     */
    public String getMessage() {
        return UnknownCommandException.MESSAGE;
    }
}

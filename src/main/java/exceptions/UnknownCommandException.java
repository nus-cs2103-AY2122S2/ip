package exceptions;

/**
 * Exception thrown when user enters a command that Duke has not been programmed to handle.
 */
public class UnknownCommandException extends Throwable{
    public UnknownCommandException(String message) {
        super(message);
    }
}

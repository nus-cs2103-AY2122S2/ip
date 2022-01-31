package duke.exceptions;

/**
 * Exception thrown when user enters a command that duke.Duke has not been programmed to handle.
 */
public class UnknownCommandException extends Throwable {
    public UnknownCommandException(String message) {
        super(message);
    }
}

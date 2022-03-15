package bob;

/**
 * Thrown when insufficient arguments are provided to Bob.
 */
public class InsufficientArgumentsException extends Exception {
    public InsufficientArgumentsException(String message, Throwable error) {
        super(message, error);
    }

    public InsufficientArgumentsException(Throwable error) {
        super(error);
    }

    public InsufficientArgumentsException(String message) {
        super(message);
    }
}

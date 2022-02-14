package bobby.exception;

/**
 * Abstract class for this project.
 * All Exceptions should extend from this class.
 */
public abstract class BobbyException extends IllegalArgumentException {
    /**
     * Constructor for BobbyException
     *
     * @param message Indicates the error type.
     */
    public BobbyException(String message) {
        super(message);
    }

    /**
     * Uses the error type to choose which message to return.
     *
     * @return Appropriate message according to the error type.
     */
    @Override
    public abstract String toString();
}

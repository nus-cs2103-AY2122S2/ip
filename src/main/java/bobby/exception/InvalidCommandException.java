package bobby.exception;

/**
 * Represents an 'invalid command' exception
 */
public class InvalidCommandException extends BobbyException {

    /**
     * Constructor for InvalidCommandException
     *
     * @param message short message to indicate error type.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Invalid command!\n(╯°□°)╯︵ ┻━┻ ︵ ╯(°□° ╯)";
    }
}

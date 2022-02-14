package bobby.exception;

/**
 * Represents a 'invalid number' exception
 */
public class InvalidNumberException extends BobbyException {
    /* Flag to indicate the type of message to be sent */
    private String errorType;

    /**
     * Constructor for InvalidNumberException
     *
     * @param message short message to indicate error type.
     */
    public InvalidNumberException(String message) {
        super(message);
        errorType = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String errorMsg;
        switch (errorType) {
        case "OOB":
            errorMsg = "Number too big, count properly!";
            break;
        case "negative":
            errorMsg = "Invalid number man, cannot be 0 or negative";
            break;
        case "letter":
            errorMsg = "Invalid index";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

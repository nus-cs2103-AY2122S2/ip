package bobby.exception;

/**
 * Represents a 'mark' exception
 */
public class MarkException extends BobbyException {
    /* Flag to indicate the type of message to be sent */
    private String errType;

    /**
     * Constructor for MarkException
     *
     * @param message short message to indicate error type.
     */
    public MarkException(String message) {
        super(message);
        errType = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String errorMsg;
        switch (errType) {
        case "empty":
            errorMsg = "Which task?";
            break;
        case "alr_marked":
            errorMsg = "This task is already marked as done";
            break;
        case "alr_unmarked":
            errorMsg = "This task is already marked as undone";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

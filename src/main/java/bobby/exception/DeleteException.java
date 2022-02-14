package bobby.exception;

/**
 * Represents a 'delete' exception
 */
public class DeleteException extends BobbyException {
    /* Flag to indicate the type of message to be sent */
    private String errorType;

    /**
     * Constructor for DeleteException
     *
     * @param message short message to indicate error type.
     */
    public DeleteException(String message) {
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
        case "empty":
            errorMsg = "Delete which task?\nTell me by inputting delete {number}";
            break;
        case "list_empty":
            errorMsg = "List is already empty, nothing to delete";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

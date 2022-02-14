package bobby.exception;

/**
 * Represents a 'delete' exception
 */
public class FindException extends BobbyException {
    /* Flag to indicate the type of message to be sent */
    private String errType;

    /**
     * Constructor for FindException
     *
     * @param message short message to indicate error type.
     */
    public FindException(String message) {
        super(message);
        errType = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String errorString;
        switch(errType) {
        case "empty_command": errorString = "What is the keyword?";
            break;
        case "empty_tasks": errorString = "You have completely no tasks!";
            break;
        default: errorString = "Cannot find any task matching the keyword.";
        }
        return errorString;
    }
}

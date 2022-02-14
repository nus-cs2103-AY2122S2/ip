package bobby.exception;

/**
 * Represents a 'storage' exception
 */
public class StorageException extends BobbyException {
    /* Flag to indicate the type of message to be sent */
    private String errType;

    /**
     * Constructor for MarkException
     *
     * @param message short message to indicate error type.
     */
    public StorageException(String message) {
        super(message);
        errType = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String errorMsg;
        switch(errType) {
        case "no_file": errorMsg = "File not found. Creating file now. (づ｡◕‿‿◕｡)づ";
            break;
        case "empty_file": errorMsg = "Empty file found! Nothing to import";
            break;
        default: errorMsg = "Something went wrong. Couldn't save or load tasks";
        }
        return errorMsg;
    }
}

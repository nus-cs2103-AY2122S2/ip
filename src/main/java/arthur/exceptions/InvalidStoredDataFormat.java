package arthur.exceptions;

/**
 * Thrown when the data stored in the data file is invalid/incorrect.
 */
public class InvalidStoredDataFormat extends Exception {
    private static final String INVALID_DATA_FORMAT_ERROR_MESSAGE = "Invalid data format "
            + "in storage data file. \nPlease clear data file and try again.";

    /**
     * Constructs the exception with the default message.
     */
    public InvalidStoredDataFormat() {
        super(INVALID_DATA_FORMAT_ERROR_MESSAGE);
    }
}

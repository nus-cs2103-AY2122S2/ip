package duke.exceptions;

/**
 * Represents an exception that is thrown when there are errors when parsing
 * the storage file.
 */
public class StorageException extends DukeException {
    private final String errorCode;

    /**
     * Instantiates a StorageException.
     */
    public StorageException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the detail message of the error that has occurred when trying to
     * read or write from the storage file.
     * @return Detail message of an StorageException.
     */
    @Override
    public String getMessage() {
        String message = "";
        switch (errorCode) {
        case "DIRECTORY_CREATION_ERROR":
            message = "Error with directory initialization!";
            break;
        case "FILE_NOT_FOUND":
            message = "Nani?? Your file cannot be found!"
                    + " Please make sure it exists in the correct folder!";
            break;
        case "FILE_READ_ERROR":
            message = "Sumimasen! We had some problems reading your file!";
            break;
        default:
            assert false : errorCode;
        }
        return message;
    }
}

package duke.exception;

/**
 * Represents an exception that happens when dealing with file storage.
 */
public class StorageException extends Exception {
    /**
     * Creates a StorageException with the given message.
     *
     * @param message Exception message.
     */
    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}

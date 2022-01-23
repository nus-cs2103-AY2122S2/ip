package duke.exception;

/**
 * StorageException representing Exceptions that arise due to interactions with Storage.
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
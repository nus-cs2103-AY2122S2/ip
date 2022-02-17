package storage;

/**
 * Represents exceptions that are caused by failures that occurs when performing
 * storage operations.
 */
public class StorageOperationException extends Exception {
    private static final String ERR_MSG_PREFIX = "StorageOperationException: ";

    /**
     * Returns an exception which describes the reason for a failure
     * when performing a storage operation.
     *
     * @param message describes the reason for a storage operation failure.
     */
    public StorageOperationException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of the exception with a relevant
     * prefix for easier identification of exception type.
     *
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return StorageOperationException.ERR_MSG_PREFIX + super.getMessage();
    }
}

package storage;

public class StorageOperationException extends Exception {
    private static final String ERR_MSG_PREFIX = "StorageOperationException: ";

    public StorageOperationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return StorageOperationException.ERR_MSG_PREFIX + super.getMessage();
    }
}

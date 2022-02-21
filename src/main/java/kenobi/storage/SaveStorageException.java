package kenobi.storage;

public class SaveStorageException extends StorageException {
    public SaveStorageException(String msg) {
        super(" :tasks could not be saved" + msg);
    }
}

package kenobi.storage;

public class SaveStorageException extends StorageException {
    public SaveStorageException(String msg) {
        super(" :fail to save tasks" + msg);
    }
}

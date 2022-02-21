package kenobi.storage;

public class LoadStorageException extends StorageException {
    public LoadStorageException(String msg) {
        super(" :fail to load save files" + msg);
    }
}

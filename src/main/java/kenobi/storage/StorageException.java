package kenobi.storage;

public class StorageException extends Exception {
    public StorageException(String msg) {
        super("There was a problem with the storage" + msg);
    }
}

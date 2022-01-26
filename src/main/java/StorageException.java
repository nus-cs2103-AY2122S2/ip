public class StorageException extends BobbyException {

    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Something went wrong. Couldn't save tasks";
    }
}

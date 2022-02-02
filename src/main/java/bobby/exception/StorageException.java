package bobby.exception;

public class StorageException extends BobbyException {
    private String errType;

    public StorageException(String message) {
        super(message);
        errType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch(errType) {
        case "no_file": errorMsg = "File not found. Creating file now. (づ｡◕‿‿◕｡)づ";
            break;
        case "empty_file": errorMsg = "Empty file found! Nothing to import";
            break;
        default: errorMsg = "Something went wrong. Couldn't save or load tasks";
        }
        return errorMsg;
    }
}

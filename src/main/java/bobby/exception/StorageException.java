package bobby.exception;

public class StorageException extends BobbyException {
    private String errtype;

    public  StorageException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch(errtype) {
        case "no_file": error_string = "\tFile not found. Creating file now. (づ｡◕‿‿◕｡)づ";
            break;
        case "empty_file": error_string = "Empty file found! Nothing to import";
            break;
        default: error_string = "\tSomething went wrong. Couldn't save or load tasks";
        }
        return error_string;
    }
}

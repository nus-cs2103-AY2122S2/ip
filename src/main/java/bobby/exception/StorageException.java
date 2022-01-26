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
        case "file_not_found": error_string = "\tFile not found. Creating file now. (づ｡◕‿‿◕｡)づ";
            break;
        case "io": error_string = "";
            break;
        case "storage": error_string = "\t";
            break;
        default: error_string = "\tSomething went wrong. Couldn't save or load tasks";
        }
        return error_string;
    }
}

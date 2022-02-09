package bobby.exception;

public class DeleteException extends BobbyException {
    private String errorType;

    public DeleteException(String message) {
        super(message);
        errorType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errorType) {
        case "empty":
            errorMsg = "Delete which task?\nTell me by inputting delete {number}";
            break;
        case "list_empty":
            errorMsg = "List is already empty, nothing to delete";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

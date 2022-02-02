package bobby.exception;

public class DeleteException extends BobbyException {
    private String errType;

    public DeleteException(String message) {
        super(message);
        errType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errType) {
        case "empty":
            errorMsg = "Delete which task?\nTell me by inputting delete {number}";
            break;
        case "letter":
            errorMsg = "That is neither a number nor \"all\"";
            break;
        case "OOB":
            errorMsg = "Number too big, count properly!";
            break;
        case "negative":
            errorMsg = "Invalid number man, cannot be 0 or negative";
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

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
            errorMsg = "\tDelete which task?";
            break;
        case "letter":
            errorMsg = "\tThat is neither a number nor \"all\"";
            break;
        case "OOB":
            errorMsg = "\tNumber too big, count properly!";
            break;
        case "negative":
            errorMsg = "\tInvalid number man, cannot be 0 or negative";
            break;
        case "list_empty":
            errorMsg = "\tList is already empty, nothing to delete";
            break;
        default:
            errorMsg = "\tI cannot understand this..";
        }
        return errorMsg;
    }
}

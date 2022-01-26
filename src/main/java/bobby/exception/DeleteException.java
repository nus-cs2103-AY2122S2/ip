package bobby.exception;

public class DeleteException extends BobbyException {
    private String errtype;

    public DeleteException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch (errtype) {
        case "empty":
            error_string = "\tDelete which task?";
            break;
        case "letter":
            error_string = "\tThat is neither a number nor \"all\"";
            break;
        case "OOB":
            error_string = "\tNumber too big, count properly!";
            break;
        case "negative":
            error_string = "\tInvalid number man, cannot be 0 or negative";
            break;
        case "list_empty":
            error_string = "\tList is already empty, nothing to delete";
            break;
        default:
            error_string = "\tI cannot understand this..";
        }
        return error_string;
    }
}

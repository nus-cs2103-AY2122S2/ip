package bobby.exception;

public class MarkException extends BobbyException {
    private String errType;

    public MarkException(String message) {
        super(message);
        errType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errType) {
        case "empty":
            errorMsg = "\tWhich task?";
            break;
        case "letter":
            errorMsg = "\tC'mon, a letter is not a number dude";
            break;
        case "OOB":
            errorMsg = "\tNumber too big, count properly!";
            break;
        case "negative":
            errorMsg = "\tInvalid number man, cannot be 0 or negative";
            break;
        case "alr_marked":
            errorMsg = "\tThis task is already marked as done";
            break;
        case "alr_unmarked":
            errorMsg = "\tThis task is already marked as undone";
            break;
        default:
            errorMsg = "\tI cannot understand this..";
        }
        return errorMsg;
    }
}

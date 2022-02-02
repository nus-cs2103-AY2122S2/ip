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
            errorMsg = "Which task?";
            break;
        case "letter":
            errorMsg = "C'mon, a letter is not a number dude";
            break;
        case "OOB":
            errorMsg = "Number too big, count properly!";
            break;
        case "negative":
            errorMsg = "Invalid number man, cannot be 0 or negative";
            break;
        case "alr_marked":
            errorMsg = "This task is already marked as done";
            break;
        case "alr_unmarked":
            errorMsg = "This task is already marked as undone";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

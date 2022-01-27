package bobby.exception;

public class DeadlineException extends BobbyException {
    private String errorType;

    public DeadlineException(String message) {
        super(message);
        errorType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errorType) {
        case "blank":
            errorMsg = "\tThere is no description after the \"deadline\" command :(";
            break;
        case "no_slash":
            errorMsg = "\tI cannot tell the due date and time, put it after a \"/\"";
            break;
        case "no_date":
            errorMsg = "\tThere is nothing after the \"/\"! When is this due?";
            break;
        case "invalid_date":
            errorMsg = "\tWrong date format! I only accept dd-mm-yyyy!";
            break;
        default:
            errorMsg = "\tI cannot understand this..";
        }
        return errorMsg;
    }
}

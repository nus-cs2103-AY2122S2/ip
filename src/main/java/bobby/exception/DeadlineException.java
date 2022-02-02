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
            errorMsg = "There is no description after the \"deadline\" command :(";
            break;
        case "no_slash":
            errorMsg = "I cannot tell the due date and time, put it after a \"/\"";
            break;
        case "no_date":
            errorMsg = "There is nothing after the \"/\"! When is this due?";
            break;
        case "invalid_date":
            errorMsg = "Wrong date format! I only accept dd-mm-yyyy!";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

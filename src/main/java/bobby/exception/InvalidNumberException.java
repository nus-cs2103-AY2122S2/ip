package bobby.exception;

public class InvalidNumberException extends BobbyException {
    private String errorType;

    public InvalidNumberException(String message) {
        super(message);
        errorType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errorType) {
        case "OOB":
            errorMsg = "Number too big, count properly!";
            break;
        case "negative":
            errorMsg = "Invalid number man, cannot be 0 or negative";
            break;
        case "letter":
            errorMsg = "Invalid index";
            break;
        default:
            errorMsg = "I cannot understand this..";
        }
        return errorMsg;
    }
}

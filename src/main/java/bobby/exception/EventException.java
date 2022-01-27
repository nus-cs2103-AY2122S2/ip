package bobby.exception;

public class EventException extends BobbyException {
    private String errType;

    public EventException(String message) {
        super(message);
        this.errType = message;
    }

    @Override
    public String toString() {
        String errorMsg;
        switch (errType) {
        case "blank":
            errorMsg = "\tThere is no description after the event command :(";
            break;
        case "no_slash":
            errorMsg = "\tI cannot tell the time of the event, put the time after a \"/\"";
            break;
        case "no_date":
            errorMsg = "\tThere is nothing after the \"/\"! When is this event happening?";
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

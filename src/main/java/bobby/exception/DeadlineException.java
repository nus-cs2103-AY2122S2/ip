package bobby.exception;

public class DeadlineException extends BobbyException {
    private String errtype;

    public DeadlineException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch (errtype) {
        case "blank":
            error_string = "\tThere is no description after the \"deadline\" command :(";
            break;
        case "no_slash":
            error_string = "\tI cannot tell the due date and time, put it after a \"/\"";
            break;
        case "no_date":
            error_string = "\tThere is nothing after the \"/\"! When is this due?";
            break;
        case "invalid_date":
            error_string = "\tWrong date format! I only accept dd-mm-yyyy!";
            break;
        default:
            error_string = "\tI cannot understand this..";
        }
        return error_string;
    }
}

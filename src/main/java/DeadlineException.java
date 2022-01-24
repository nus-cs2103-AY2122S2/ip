public class DeadlineException extends BobbyException {
    public String errtype;
    public DeadlineException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch (errtype) {
        case "deadline1":
            error_string = "    There is no description after the \"deadline\" command :(";
            break;
        case "deadline2":
            error_string = "    I cannot tell the due date and time, put it after a \"/\"";
            break;
        case "deadline3":
            error_string = "    There is nothing after the \"/\"! When is this due?";
            break;
        default:
            error_string = "    I cannot understand this..";
        }
        return error_string;
    }
}

public class EventException extends BobbyException {
    public String errtype;

    public EventException(String message) {
        super(message);
        this.errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch (errtype) {
        case "blank":
            error_string = "    There is no description after the event command :(";
            break;
        case "no_slash":
            error_string = "    I cannot tell the time of the event, put the time after a \"/\"";
            break;
        case "no_date":
            error_string = "    There is nothing after the \"/\"! When is this event happening?";
            break;
        case "invalid_date":
            error_string = "    Wrong date format! I only accept dd-mm-yyyy!";
            break;
        default:
            error_string = "    I cannot understand this..";
        }
        return error_string;
    }
}

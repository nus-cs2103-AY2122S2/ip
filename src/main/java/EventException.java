public class EventException extends BobbyException {
    public String errtype;
    public EventException(String message) {
        super(message);
        this.errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch(errtype) {
            case "event1": error_string = "    There is no description after the event command :("; break;
            case "event2": error_string = "    I cannot tell the time of the event, put the time after a \"/\""; break;
            case "event3": error_string = "    There is nothing after the \"/\"! When is this event happening?"; break;
            default: error_string = "    I cannot understand this..";
        }
        return error_string;
    }
}

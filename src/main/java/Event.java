public class Event extends Task {
    protected String time;

    public Event(String message, String when) {
        super(message);
        time = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}

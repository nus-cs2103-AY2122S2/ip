public class Event extends Task {
    protected String event_time;

    public Event(String description, String event_time) {
        super(description);
        this.event_time = event_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event_time + ")";
    }
}
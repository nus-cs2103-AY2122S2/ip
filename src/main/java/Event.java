public class Event extends Task {
    String time;
    String event;

    public Event(String event, String time) {
        super(event);
        this.event = event;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}

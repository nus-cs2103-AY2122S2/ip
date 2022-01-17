public class Event extends Task {

    protected String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", time);
    }
}
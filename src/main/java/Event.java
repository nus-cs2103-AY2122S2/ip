public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}



public class Event extends Task {
    private final String time;

    public Event(String title, String time, boolean status) {
        super(title, status);
        this.time = time;
    }

    public Event(String title, String time) {
        super(title, false);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

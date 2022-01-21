package duke;

public class Event extends Task {
    private String time;
    public Event(String name, String time) {
        super(name, "E", time);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

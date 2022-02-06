package duke;

/**
 * Represents an Event Task. A <code>Event</code> object corresponds to
 * a Task which contains a <code>String</code> time field corresponding
 * to when the event occurs.
 */


public class Event extends Task {
    private String time;
    public Event(String name, String time) {
        super(name, "E", time);
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
        setExtension(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

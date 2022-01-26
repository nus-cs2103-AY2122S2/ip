/**
 * The Event class extends the Task class by adding an event time.
 *
 * @author Rdac0
 */
public class Event extends Task{
    private String time;

    /**
     * Creates an Event object.
     *
     * @param name The name of the Event.
     * @param time The time of the Event.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[E]" + mark + getName() +
                " (at: " + this.time + ")";
    }
}

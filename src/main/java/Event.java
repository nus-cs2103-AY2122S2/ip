/**
 * Represents tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Event), description,
     * and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

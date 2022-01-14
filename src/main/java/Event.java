/**
 * Event class represents tasks start at a specific time
 * and ends at a specific time.
 */
public class Event extends Task {
    protected String at;

    /**
     * Class constructor specifying the task's description
     * and time period.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
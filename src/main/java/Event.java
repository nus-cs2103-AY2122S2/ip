/**
 * Event class represents tasks that start at a specific time
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

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String formatForFile() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.at + "\n";
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
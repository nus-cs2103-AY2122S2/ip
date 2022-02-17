package task;

/**
 * Represents Task which is entered with Event Prefix.
 */
public class Event extends Task {

    /**
     * Event location.
     */
    protected String at;

    /**
     * Creates a Event task.
     *
     * @param description Description of the Event task.
     * @param at Event location.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates Event based on saved data.
     *
     * @param status Status retrieved.
     * @param description Description retrieved.
     * @param tag Tag retrieved.
     * @param at Location retrieved.
     */
    public Event(String status, String description, String tag, String at) {
        super(status, description, tag);
        this.at = at;
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * event location.
     *
     * @return String representation of Task to display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * event location.
     *
     * @return String representation of Task to save.
     */
    public String toSave() {
        return "E" + super.toSave() + " : " + at;
    }
}
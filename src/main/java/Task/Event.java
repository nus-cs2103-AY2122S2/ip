package Task;

/**
 * Represents Task which is entered with Event Prefix.
 */
public class Event extends Task {
    /**
     * Location of Event.
     */
    protected String by;

    /**
     * Creates an Event task.
     *
     * @param description Description of the Event task.
     * @param by Deadline Date.
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
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
        return "[E]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * event location.
     *
     * @return String representation of Task to save.
     */
    public String toSave() {
        return "E" + super.toSave() + " : " + by;
    }
}
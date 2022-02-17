package task;

/**
 * Represents Task which is entered with Event Prefix.
 */
public class Event extends Task {

    /** Location of event. */
    protected String at;

    /**
     * Instantiates an event task with the description of event
     * and location of event.
     *
     * @param description Description of the event task.
     * @param at Event location.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Instantiates an event task with details of the task retrieved
     * from the stored file.
     *
     * @param data Array containing details of event from stored file.
     */
    public Event(String[] data) {
        super(data[1], data[2], data[3]);
        this.at = data[4];
    }

    /**
     * Returns the string representation with details
     * on the task type, task status, task description and
     * event location.
     *
     * @return String representation of event to be displayed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, task status, task description and
     * event location.
     *
     * @return String representation of event to be stored.
     */
    public String toSave() {
        return "E" + super.toSave() + " : " + at;
    }
}
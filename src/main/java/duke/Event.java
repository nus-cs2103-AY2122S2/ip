package duke;

/**
 * Class {@code Event}
 * Extends {@code Task}
 */
public class Event extends Task {

    protected String dueAt;

    /**
     * Constructor for {@code Event}
     *
     * @param description Description of the Event Task
     * @param dueAt Location of the Event Task
     */
    public Event(String description, String dueAt) {
        super(description);
        this.dueAt = dueAt;
    }

    /**
     * Returns Status Icon of current task as a String representation
     *
     * @return String representation of current task status icon
     */
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns String representation of {@code Event} Task.
     *
     * @return String representation of task of format {@code StatusIcon + Description}
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + dueAt + ")";
    }
}

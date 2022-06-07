package duke.task;

/**
 * Represents an event task that needs to be done.
 */
public class Event extends Task {
    private String at;

    /**
     * Class constructor.
     *
     * @param description description of this event.
     * @param at the time of this event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this task formatted as the way it is to be stored in disk.
     *
     * @return the string representation of this task formatted as the way it is to be stored in disk.
     */
    @Override
    public String toFileFormat() {
        return "E," + super.toFileFormat() + "," + at + "," + getStatusIcon();
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

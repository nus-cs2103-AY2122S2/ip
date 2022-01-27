package task;

/**
 * Represents a type of Task - Event.
 * Allows for venues to be specified which distinguishes
 * itself from an Deadline or Todo.
 */
public class Event extends Task {

    protected String at;

    /**
     * Class constructor.
     * @param description Description of event
     * @param at location of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    /**
     * Returns event details.
     *
     * @return Output string to indicate event's details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
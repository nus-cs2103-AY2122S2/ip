/**
 * Represent the event of task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description description of the task.
     * @param at by when the event at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation of event.
     *
     * @return [E] with the status and description of the task,
     *         and at when.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}


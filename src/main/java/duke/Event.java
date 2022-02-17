package duke;

/**
 * One of the three types of tasks that can be created.
 * Indicates a Event to be attended at a certain time.
 */
public class Event extends Task {
    /**
     * String representing when the event is.
     */
    protected String when;

    /**
     * Constructor for an Event.
     *
     * @param description description of the task
     * @param when when the event is
     */
    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    /**
     * {@inheritDoc}
     * @return the String representation of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}

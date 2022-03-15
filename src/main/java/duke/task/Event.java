package duke.task;

/**
 * Represents an event with a description and a time.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a new Event.
     *
     * @param description The description of the Event.
     * @param at String representing the time that the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

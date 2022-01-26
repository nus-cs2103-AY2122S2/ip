package duke.task;

/**
 * A class represent for an event.
 */
public class Event extends Task{
    protected String at;

    /**
     * Class constructor with the description and the date time
     * Creates an undone Event.
     *
     * @param description Event description
     * @param at Event date time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date time of Event
     * @return Event date time
     */
    public String getAt() {
        return at;
    }

    /**
     * Overrides toString method to make a string including prefix, status icon, description and date.
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

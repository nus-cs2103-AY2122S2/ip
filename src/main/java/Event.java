/**
 * Tasks that has as specific start time and end times.
 */
public class Event extends Task {

    /**
     * String representation of the event start and
     * end dates/times.
     */
    protected String at;

    /**
     * Constructor to create a deadline task.
     *
     * @param description text description of the Event.
     * @param at start and end dates/times.
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Outputs the string to represent Event with
     * description and at dates/times.
     *
     * @return formatted string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
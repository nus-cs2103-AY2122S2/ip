package duke.task;

/**
 * Represents an Event task. An Event Object corresponds to a String description of the event
 * and String timing of which the event is occuring at.
 */
public class Event extends Task {
    private String dateAndTime;

    /**
     * Constructs an event task.
     * @param description Description of event task.
     * @param dateAndTime Date and time string of event task.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns a String representation of an Event task in the desired format.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + dateAndTime + ")";
    }
}

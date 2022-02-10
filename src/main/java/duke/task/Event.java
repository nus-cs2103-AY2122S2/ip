package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. An Event Object corresponds to a String description of the event
 * and String timing of which the event is occuring at.
 */
public class Event extends Task {
    private final LocalDateTime dateAndTime;

    /**
     * Constructs an event task.
     * @param description Description of event task.
     * @param dateAndTime Date and time string of event task.
     */
    public Event(String description, LocalDateTime dateAndTime) {
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
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        String outputDateTime = dateAndTime.format(outputFormat);
        return "[E]" + super.toString() + " (at:" + outputDateTime + ")";
    }
}

package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class.
 *
 * @author Jet Tan
 */
public class Event extends Task {
    private final LocalDateTime at;

    /**
     * Constructor for a new instance of Event, containing the description and date of the event.
     *
     * @param description The description of the event
     * @param at The date of the event
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.at = LocalDateTime.parse(at, format);
    }

    /**
     * Returns a string containing task type, done status and description along with time of event
     *
     * @return string containing task type, done status and description along with time of event
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + at.format(displayFormat) + ")";
    }
}
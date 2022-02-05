package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a date and time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs an event.
     * @param description The event description.
     * @param at The event date and time.
     * @param isDone The event status, whether it is done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Event(String description, String at) {
        this(description, at, false);
    }

    /**
     * Returns the event as a string which can be saved and loaded as an event again.
     * @return Event as a string which can be saved and loaded as an event again.
     */
    @Override
    public String save() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E | " + super.save() + " | " + dateTime + System.lineSeparator();
    }

    /**
     * Returns the event as a readable string.
     * @return Event as a readable string.
     */
    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")" + "\n";
    }
}

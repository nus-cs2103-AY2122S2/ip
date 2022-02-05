package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tag.Tag;

/**
 * The type Event.
 */
public class Event extends Task {

    /**
     * The At date.
     */
    protected LocalDateTime atDateTime;

    /**
     * Instantiates a new Event with tag.
     *
     * @param description the description
     * @param atDateTime  the at date time
     * @param tag         the tag
     */
    public Event(String description, LocalDateTime atDateTime, Tag tag) {
        super(description, tag);
        this.atDateTime = atDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (at: " + formatter.format(atDateTime) + ")";
    }
}


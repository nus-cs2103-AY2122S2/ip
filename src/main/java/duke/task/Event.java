package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Event.
 */
public class Event extends Task {

    /**
     * The At date.
     */
    protected LocalDateTime atDateTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param atDateTime          the at
     */
    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (at: " + formatter.format(atDateTime) + ")";
    }
}


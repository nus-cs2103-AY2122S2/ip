package karen.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/at) date in which the Event occurs at
 */
public class Event extends Task {
    protected LocalDateTime atDate;

    /**
     * Constructor for Event
     *
     * @param description Description of what Event is for
     * @param at A "yyyy-mm-dd" string for the Event to be completed at
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        atDate = at;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("E|%s|%s|%s", isDone, getDescription(), formatDate(atDate));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return "[E]" + super.toString() + " (at: " + atDate.format(formatter) + ")";
    }
}

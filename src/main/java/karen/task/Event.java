package karen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/at) date in which the Event occurs at
 */
public class Event extends Task {
    protected LocalDate atDate;

    /**
     * Constructor for Event
     *
     * @param description Description of what Event is for
     * @param at A "yyyy-mm-dd" string for the Event to be completed at
     */
    public Event(String description, String at) {
        super(description);
        atDate = parseDate(at);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("E|%s|%s|%s", isDone, getDescription(), atDate);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}

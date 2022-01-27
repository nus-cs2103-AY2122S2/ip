package pikabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with an event date.
 */
public class Event extends Task {

    protected LocalDate dateAt;

    /**
     * Constructs an Event.
     *
     * @param description Description of the Event task.
     * @param dateAt Date of event.
     */
    public Event(String description, LocalDate dateAt) {
        super(description);
        this.dateAt = dateAt;
    }

    /**
     * Converts Event to a formatted string.
     *
     * @return Formatted string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
            this.dateAt.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")) + ")";
    }

}

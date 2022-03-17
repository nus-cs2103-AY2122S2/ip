package pyke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    /**
     * The default constructor
     *
     * @param description
     * @param eventTime
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        if (eventTime == null) {
            throw new NullPointerException("The event date cannot be null value");
        }
        this.eventTime = eventTime;
    }

    /**
     * Formats the event class to a style used in local files for saving
     *
     * @return the formatted string for saving
     */
    @Override
    public String toSavedFile() {
        return "D | " + super.toSavedFile() + " | " + this.eventTime;
    }

    /**
     * Formats the event class to a style used for output
     *
     * @return the formatted string for output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

package pyke.task;

import pyke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Format the event class to a style used in local files for saving
     *
     * @return the formatted string for saving
     */
    @Override
    public String toSavedFile() {
        return "D | " + super.toSavedFile() + " | " + this.eventTime;
    }

    /**
     * Format the event class to a style used for output
     *
     * @return the formatted string for output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with its description and date of event.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Creates an Event task with its description and date of event.
     * 
     * @param description description of event task.
     * @param date date of event.
     */
    public Event(String description, LocalDate date) {
        super(description, 'E');
        this.date = date;
    }

    /**
     * Gets the date of event of this task.
     * 
     * @return Returns the date of event of this task.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a formatted string of an Event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.getStatusIcon(),
                super.getDescription(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

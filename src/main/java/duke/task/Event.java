package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Creates an Event object with description of the task.
     * @param description Description of the deadline task.
     * @param at Date in which the task is due.
     */
    public Event(String description, LocalDate at) {
        super(description, "E", at);
        this.at = at;
    }

    /**
     * Prints out the details of the Event task.
     * @return A string with details of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

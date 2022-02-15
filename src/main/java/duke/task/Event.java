package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private final LocalDate date;

    /**
     * Creates an Event object with description of the task.
     *
     * @param description Description of the deadline task.
     * @param date Date in which the task is due.
     */
    public Event(String description, LocalDate date) {
        super(description, "E", date);
        this.date = date;
        assert description != null : "Description of event cannot be null.";
        assert description.length() > 0 : "Body of event cannot be empty.";
        assert date != null : "Event date cannot be null.";
    }

    /**
     * Prints out the details of the Event task.
     *
     * @return A string with details of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

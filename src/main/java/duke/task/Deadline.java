package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private final LocalDate date;

    /**
     * Creates a Deadline object with description of the task.
     *
     * @param description Description of the deadline task.
     * @param date Date in which the task is due.
     */
    public Deadline(String description, LocalDate date) {
        super(description, "D", date);
        this.date = date;
        assert description != null : "Description of deadline cannot be null.";
        assert description.length() > 0 : "Body of deadline cannot be empty.";
        assert date != null : "Deadline date cannot be null.";
    }

    /**
     * Prints out the details of the Deadline task.
     *
     * @return A string with details of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

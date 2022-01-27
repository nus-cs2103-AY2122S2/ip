package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Creates a Deadline object with description of the task.
     * @param description Description of the deadline task.
     * @param by Date in which the task is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description, "D", by);
        this.by = by;
    }

    /**
     * Prints out the details of the Deadline task.
     * @return A string with details of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

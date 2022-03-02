package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * One of the three types of tasks that can be created.
 * Indicates a task due by a certain deadline.
 */
public class Deadline extends Task {
    /**
     * Date object representing when the task is due.
     */
    protected LocalDate by;

    /**
     * Constructor for a Deadline.
     *
     * @param description description of the task
     * @param by when the deadline is
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     * @return the String representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }
}

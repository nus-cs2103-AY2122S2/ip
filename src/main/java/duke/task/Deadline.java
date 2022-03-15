package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline.
     *
     * @param description The description of the Deadline.
     * @param by The date representing the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String formatDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }
}

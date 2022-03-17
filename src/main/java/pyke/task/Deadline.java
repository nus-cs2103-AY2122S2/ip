package pyke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline class.
     *
     * @param description the description of the deadline.
     * @param by the time of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        if (by == null) {
            throw new NullPointerException("The deadline date cannot be null value");
        }
        this.by = by;
    }

    /**
     * Formats the deadline class to a style used in local files for saving.
     *
     * @return the formatted string for saving.
     */
    @Override
    public String toSavedFile() {
        return "D | " + super.toSavedFile() + " | " + this.by;
    }

    /**
     * Formats the deadline class to a style used for output.
     *
     * @return the formatted string for output.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

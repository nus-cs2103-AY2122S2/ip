package pyke.task;

import pyke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Format the deadline class to a style used in local files for saving
     *
     * @return the formatted string for saving
     */
    @Override
    public String toSavedFile() {
        return "D | " + super.toSavedFile() + " | " + this.by;
    }

    /**
     * Format the deadline class to a style used for output
     *
     * @return the formatted string for output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
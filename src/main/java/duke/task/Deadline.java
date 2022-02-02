package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that needs to be done.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Class constructor.
     *
     * @param description description of this task.
     * @param date deadline of this task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        by = date;
    }

    /**
     * Returns the string representation of this task formatted as the way it is to be stored in disk.
     *
     * @return the string representation of this task formatted as the way it is to be stored in disk.
     */
    @Override
    public String toFileFormat() {
        return "D," + super.toFileFormat() + "," + by + "," + getStatusIcon();
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

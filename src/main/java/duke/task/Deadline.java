package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;
    }

    /**
     * Gets format to display deadline task on file
     *
     * @return format to display deadline task on file
     */
    @Override
    public String getFileFormat() {
        return "D" + super.getFileFormat() + " | " + by;
    }

    /**
     * Gets general format to display deadline task
     *
     * @return general format to display deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
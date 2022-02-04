package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    /**
     * Constructs a deadline.
     * @param description The deadline description.
     * @param by The deadline date and time.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * Returns the deadline as a string which can be saved and loaded as a deadline again.
     *
     * @return Deadline as a string which can be saved and loaded as a deadline again.
     */
    @Override
    public String save() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D | " + super.save() + " | " + dateTime + System.lineSeparator();
    }

    /**
     * Returns the deadline as a readable string.
     *
     * @return Deadline as a readable string.
     */
    @Override
    public String toString() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}

package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    private LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the deadline.
     * @param by Date and time by which it should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets the deadline datetime to be 1 day later.
     */
    public void snooze() {
        this.by = this.by.plusDays(1);
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}

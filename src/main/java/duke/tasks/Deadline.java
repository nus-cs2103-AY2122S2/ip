package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline task.
     *
     * @param description Task description.
     * @param by The date time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task.
     *
     * @param description Task description.
     * @param by The date time of the deadline.
     * @param isDone If the task has is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/y HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d y - HHmm")) + ")";
    }

    @Override
    public String simpleString() {
        String flag = isDone ? "1" : "0";
        return "D" + flag + description + " | " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}

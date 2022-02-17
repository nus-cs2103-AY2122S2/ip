package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Constructs a deadline task.
     *
     * @param description Task description.
     * @param dueDate The date time of the deadline.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a deadline task.
     *
     * @param description Task description.
     * @param dueDate The date time of the deadline in dd/mm/yyyy hhmm.
     * @param isDone If the task has is done or not.
     */
    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("d/M/y HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM d y - HHmm")) + ")";
    }

    @Override
    public String toCompactStorageString() {
        String flag = isDone ? "1" : "0";
        return "D" + flag + description + " | " + dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}

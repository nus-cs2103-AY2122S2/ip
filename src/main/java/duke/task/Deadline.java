package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a deadline task with the given data.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Creates a deadline task with the given data.
     *
     * @param description Description of the task.
     * @param isDone Status of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String formatForFile() {
        return super.formatForFile() + " | " + this.by.toString();
    }

    /**
     * Returns type, description and deadline of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

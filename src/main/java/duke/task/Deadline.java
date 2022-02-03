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
     * @param description Description of the task to be created.
     * @param by Deadline of the task to be created.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Creates a deadline task with the given data.
     *
     * @param description Description of the task to be created.
     * @param isDone Status of the task to be created.
     * @param by Deadline of the task to be created.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        return by.equals(deadline.by);
    }
}

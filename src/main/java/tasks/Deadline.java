package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents work that needs to be done by a given deadline.
 */
public class Deadline extends Task {
    /** Represents the expected format of date inputs for deadlines. */
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate by;

    /**
     * Returns a Deadline object that contains the details of the
     * work that needs to be done by a given deadline.
     *
     * @param description describes the work that needs to be done.
     * @param by is the date to complete the task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private Deadline(String description, LocalDate by, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
        this.by = by;
    }

    /**
     * Returns the date to complete the current task by.
     *
     * @return The date to complete the task by.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Duplicates the current Deadline task.
     *
     * @return A duplicate of the current Deadline task.
     */
    @Override
    public Deadline clone() {
        return new Deadline(super.description, this.by, super.isDone, super.priority);
    }

    /**
     * Describes the current Deadline task with a prefix indicating its task type,
     * as well as the date to complete the task by.
     *
     * @return A string representation of the current Deadline task's description,
     * completion status, and the date to complete the task by.
     */
    @Override
    public String toString() {
        final String taskDescription = "[D]" + super.toString();
        final String dueDate = this.by.format(Deadline.DATE_DISPLAY_FORMAT);
        return taskDescription + " (by: " + dueDate + ")";
    }
}

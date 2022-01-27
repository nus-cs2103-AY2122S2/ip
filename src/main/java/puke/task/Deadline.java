package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initialises the task with the task name and deadline.
     *
     * @param taskName Name of the task.
     * @param datetime Deadline of the task.
     */
    public Deadline(String taskName, LocalDateTime datetime) {
        super(taskName);
        this.by = datetime;
    }

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    public String toSaveString() {
        return "D@@" + (this.isDone() ? "1@@" : "0@@")
                + this.name + "@@" + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}

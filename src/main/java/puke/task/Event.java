package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Initialises the task with the task name and deadline.
     *
     * @param taskName Name of the task.
     * @param datetime Date/time of occurrence of the task.
     */
    public Event(String taskName, LocalDateTime datetime) {
        super(taskName);
        this.at = datetime;
    }

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    public String toSaveString() {
        return "E@@" + (this.isDone() ? "1@@" : "0@@")
                + this.name + "@@" + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}

package puke.task;

import java.time.LocalDateTime;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Initialises the task with the task name.
     *
     * @param taskName Name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns null since it does not have a date/time associated.
     *
     * @return null.
     */
    public LocalDateTime getDate() {
        return null;
    };

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    public String toSaveString() {
        return "T@@" + (this.isDone() ? "1@@" : "0@@") + this.name;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

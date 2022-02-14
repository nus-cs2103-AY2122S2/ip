package bobby.task;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents the Task object.
 */
public class Task implements Serializable, Comparable<Task> {
    private final String taskName;
    private boolean isDone;
    private LocalDate date;

    /**
     * Constructor for Task
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
        date = null;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status and appends it with the task name.
     *
     * @return Task status with the task name.
     */
    @Override
    public String toString() {
        return getStatus() + taskName;
    }

    /**
     * Earlier dates will be sorted before the later dates.
     *
     * @param t The task to be compared with.
     * @return -1 if current task is before t, 1 if after t and 0 otherwise.
     */
    @Override
    public int compareTo(Task t) {
        if (this.date == null && t.date == null) {
            return 0;
        } else if (this.date == null) {
            return 1;
        } else if (t.date == null) {
            return -1;
        } else if (this.date.isBefore(t.date)) {
            return -1;
        } else if (this.date.isAfter((t.date))) {
            return 1;
        } else if (this.date.isEqual(t.date)) {
            return 0;
        }
        return 0;
    }
}

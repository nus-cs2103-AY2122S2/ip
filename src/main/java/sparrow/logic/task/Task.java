package sparrow.logic.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sparrow.model.Priority;
import sparrow.model.Status;

/**
 * Represents a task.
 */
public class Task {
    private final String description;
    private Status status;
    private Priority priority;
    /**
     * Constructs a task.
     * @param d The task description.
     * @param s The task status.
     * @param p The task priority.
     */
    public Task(String d, Status s, Priority p) {
        description = d;
        status = s;
        priority = p;
    }

    /**
     * Constructs a task with unspecified status and priority, which defaults to NOT_DONE and MEDIUM respectively.
     * @param d The task description.
     */
    public Task(String d) {
        this(d, Status.NOT_DONE, Priority.MEDIUM);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status as a string.
     *
     * @return "X" if the task is done, and " " if it is not.
     */
    public String statusToString() {
        return (status == Status.DONE ? "X" : " ");
    }

    /**
     * Returns the task priority as a string.
     *
     * @return The task priority as a string.
     */
    public String priorityToString() {
        switch (priority) {
        case LOW:
            return "P3";
        case HIGH:
            return "P1";
        default:
            return "P2";
        }
    }

    /**
     * Changes the task status to DONE.
     *
     */
    public void mark() {
        status = Status.DONE;
    }

    /**
     * Changes the task status to NOT_DONE.
     */
    public void unmark() {
        status = Status.NOT_DONE;
    }

    /**
     * Sets the task priority.
     * @param p The task priority.
     */
    public void prioritise(Priority p) {
        priority = p;
    }

    /**
     * Returns the task as a string which can be saved and loaded as a task again.
     *
     * @return Task as a string which can be saved and loaded as a task again.
     */
    public String save() {
        int s = 0;
        if (status == Status.DONE) {
            s = 1;
        }
        return s + " | " + description;
    }

    /**
     * Returns the task priority as a string to be saved.
     *
     * @return The task priority as a string to be saved.
     */
    public String savePriority() {
        switch(priority) {
        case LOW:
            return "0";
        case HIGH:
            return "2";
        default:
            return "1";
        }
    }

    /**
     * Returns the task as a readable string.
     *
     * @return Task as a readable string.
     */
    @Override
    public String toString() {
        return "[" + priorityToString() + "] " + "[" + statusToString() + "] " + description;
    }

    public LocalDateTime parseDateTime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

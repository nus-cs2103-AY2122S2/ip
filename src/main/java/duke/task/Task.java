package duke.task;

import java.time.LocalDate;

/**
 * Task class represents the tasks created by the user.
 * It keeps track of the task's description and its status.
 */
public class Task {
    private String description;
    private boolean isDone;
    private TaskType type;

    /**
     * Creates a task with the given data.
     *
     * @param description Description of the task to be created.
     * @param type Type of the task to be created.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Creates a task with the given data.
     *
     * @param description Description of the task to be created.
     * @param isDone Status of the task to be created.
     * @param type Type of the task to be created.
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns status icon "X" of the task.
     * If the task is not done, " " is returned.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a prefix denoting the task type.
     *
     * @return Prefix of the task type.
     */
    public String getTypeAsPrefix() {
        switch(type) {
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "T";
        }
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Format and returns a string of the task for storage.
     *
     * @return Formatted string of the task.
     */
    public String formatForFile() {
        return getTypeAsPrefix() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

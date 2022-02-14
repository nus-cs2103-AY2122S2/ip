package juke.task;

/**
 * Abstraction for a task.
 */
public abstract class Task implements Cloneable {
    /**
     * Task description.
     */
    protected String description;

    /**
     * Task status.
     */
    protected TaskStatus status;

    /**
     * Task type.
     */
    protected TaskType type;

    /**
     * Constructor to initialize a task with a description.
     *
     * @param description Description.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Returns the status of the task.
     *
     * @return Task status.
     */
    public TaskStatus getStatus() {
        return this.status;
    }

    /**
     * Returns the icon associated with the status of the task.
     *
     * @return Task status icon.
     */
    public String getStatusIcon() {
        return this.status.getStatusIcon();
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        assert description != null;
        assert !description.isBlank();
        return this.description;
    }

    /**
     * Returns the icon associated with the type of task.
     *
     * @return Task icon.
     */
    public String getTaskIcon() {
        return type.getTaskIcon();
    }

    /**
     * Returns the string containing the task icon, status icon and task description.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + " " + this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns a clone of this task.
     * Implemented in sub-classes.
     *
     * @return Clone of this task.
     * @throws CloneNotSupportedException Should not throw error.
     */
    @Override
    public abstract Object clone() throws CloneNotSupportedException;
}

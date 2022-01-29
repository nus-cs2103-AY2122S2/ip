package juke.task;

/**
 * Abstraction for a task.
 */
public abstract class Task {
    /**
     * Task description.
     */
    protected String description;
    
    /**
     * Task status.
     */
    protected juke.task.TaskStatus status;
    
    /**
     * Constructor to initialize a task with a description.
     *
     * @param description Description.
     */
    public Task(String description) {
        this.description = description;
        this.status = juke.task.TaskStatus.NOT_DONE;
    }
    
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = juke.task.TaskStatus.DONE;
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
        return this.description;
    }
    
    /**
     * Returns the icon associated with the type of task.
     *
     * @return Task icon.
     */
    abstract String getTaskIcon();
    
    /**
     * Returns the string containing the task icon, status icon and task description.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + " " + this.getStatusIcon() + " " + this.getDescription();
    }
}
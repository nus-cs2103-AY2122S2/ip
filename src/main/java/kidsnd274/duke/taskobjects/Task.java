package kidsnd274.duke.taskobjects;

/**
 * An abstract class representing a Task
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Default constructor for a Task
     *
     * By default, task is marked as undone
     * @param taskName Task name or description
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Alternative constructor for a Task
     *
     * Takes in an additional boolean used for importing existing Tasks
     * @param taskName Task name or description
     * @param isDone Boolean which shows if the task is marked as done or not
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns the task name or description
     * @return Task name or description
     */
    @Override
    public String toString() {
        return this.taskName;
    }


    private String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the current status of the task along with it's description
     * @return String representation of the current status of the task
     */
    public String getCurrentStatus() {
        return "[" + getStatusIcon() + "] " + this;
    }

    public abstract Types getType();

    /**
     * Returns the task name or description
     * @return Task name or description
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns a boolean which shows if the task is done or not
     * @return Boolean showing if the task is done or not
     */
    public boolean isDone() {
        return isDone;
    }
}


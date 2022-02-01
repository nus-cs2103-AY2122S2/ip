package duke.tasks;

/**
 * Task is a class which represents tasks to be done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object, constructor.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the status of the task.
     * "X" means done. " " means not done.
     *
     * @return the String representation of the status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description that has been formatted.
     * Abstract method, implementation done in child classes.
     *
     * @return A String description that has been formatted.
     */
    public abstract String formatString();

    /**
     * Returns the description of the task object.
     *
     * @return The String description of the task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        if (this.getStatusIcon().equals("X")) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

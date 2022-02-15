package duke.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private Type type;

    /**
     * Represents a Task's type.
     */
    public enum Type {
        TODO, DEADLINE, EVENT
    }

    /**
     * Task's attributes.
     *
     * @param description of the task.
     * @param type        of the task.
     */
    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of the task.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * UnMark the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Status of the task if it is done.
     *
     * @return "X" if the task is done else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string of task.
     *
     * @return the string of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

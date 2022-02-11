package duke.task;

public abstract class Task {

    /**
     * Icons representing the type of task.
     */
    public enum Icon {
        /**
         * Todo
         */
        T,
        /**
         * Deadline
         */
        D,
        /**
         * Event
         */
        E
    }

    protected final String description;
    protected final boolean isDone;

    /**
     * Constructs a {@code Task} object with a description.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} object with its description and status.
     * @param description the description of the task
     * @param isDone a boolean indicating whether the task is already marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task with a "[X]" (if marked as done) or "[ ]" (otherwise) in front.
     * @return the description of the task with a "[X]" (if marked as done) or "[ ]" (otherwise) in front
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Returns a string for recording the task.
     * @return a string for recording the task
     */
    public String toStringRecord() {
        return (isDone ? 1 : 0) + " " + description;
    }

    /**
     * Returns true if the task is marked as done.
     * @return true if the task is marked as done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the icon representing the type of the task.
     * @return the icon representing the type of the task
     */
    public abstract Icon getIcon();

    /**
     * Marks the task as done.
     * @return a new task which has been marked as done
     */
    public abstract Task mark();

    /**
     * Unmarks the task.
     * @return a new task which is not marked as done
     */
    public abstract Task unmark();

}

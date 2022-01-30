package duke.task;

/**
 * Represents a task made by the user. A <code>Task</code> object cannot be
 * created as it is an abstract base class for all tasks. The task can
 * be mark as done after the user complete the specific task.
 */
public abstract class Task {
    protected String description;
    protected boolean isMark;

    /**
     * Creates an instance of a Task object.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Creates an instance of a Task object.
     *
     * @param description the description of the task.
     * @param isMark whether the task is mark.
     */
    public Task(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    /**
     * Marks the task object as done.
     */
    public void markTask() {
        isMark = true;
    }

    /**
     * Unmarks the task object as not done.
     */
    public void unmarkTask() {
        isMark = false;
    }

    /**
     * Returns the visual description of the task.
     *
     * @return description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isMark ? "X" : "  ", description);
    }

    /**
     * Returns the data of the task.
     *
     * @return data of the task.
     */
    public String toData() {
        return String.format("%d|%s", isMark ? 1 : 0, description);
    }
}

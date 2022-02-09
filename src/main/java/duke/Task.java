package duke;

/**
 * Represents a task.
 */
public class Task {

    private final String description;
    private final boolean isDone;

    /**
     * Constructs an instance of a Task class, which is unmarked by default.
     *
     * @param description A string representing the task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct an instance of a marked or unmarked task.
     *
     * @param description A string representing the task description.
     * @param isDone A boolean representing whether the task is marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Represents the marked status of the task as a String.
     *
     * @return "X" if the task is marked, " " if the task is unmarked.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Fetches the description of the task.
     *
     * @return A string containing the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     *
     * @return A marked Task with all other attributes kept the same.
     */
    public Task mark() {
        return new Task(this.description, true);
    }

    /**
     * Marks  the task as not done.
     *
     * @return An unmarked Task with all other attributes kept the same.
     */
    public Task unmark() {
        return new Task(this.description, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

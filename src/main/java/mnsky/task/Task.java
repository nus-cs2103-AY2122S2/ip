package mnsky.task;

public class Task {
    private String taskName;
    private boolean done;

    /**
     * Constructor for the Task object.
     * @param taskName The name for the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        done = true;
    }

    /**
     * Unmarks the task so it's not yet done.
     */
    public void unmark() {
        done = false;
    }

    /**
     * Returns the task name.
     * @return The task name.
     */
    public String getName() {
        return taskName;
    }

    /**
     * Returns the task name with info indicating if it's marked or not.
     * @return The task name with info indicating if it's marked or not.
     */
    public String getGenericTaskName() {
        return String.format("[%s] %s", done ? "X" : "?", taskName);
    }

    /**
     * Overrides the string representation of the task to the [T] and then the generic task name.
     * @return The overriden string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + getGenericTaskName();
    }

    /**
     * Returns the string representation to be used for the storage data.
     * @return The string representation to be used for the storage data.
     */
    public String getStorageData() {
        return toString();
    }
}

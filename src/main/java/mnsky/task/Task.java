package mnsky.task;

public class Task {
    private String taskName;
    private boolean done;

    /**
     * Creates a Task object.
     * @param name The name for the task.
     */
    public Task(String name) {
        this.taskName = name;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmarks the task so it's not yet done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns the task name.
     * @return The task name.
     */
    public String getName() {return this.taskName;}

    /**
     * Returns the task name with info indicating if it's marked or not.
     * @return The task name with info indicating if it's marked or not.
     */
    public String getGenericTaskName() {
        return String.format("[%s] %s", this.done ? "X" : "?", this.taskName);
    }

    /**
     * Overrides the string representation of the task to the [T] and then the generic task name.
     * @return The overriden string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + this.getGenericTaskName();
    }

    /**
     * Returns the string representation to be used for the storage data.
     * @return The string representation to be used for the storage data.
     */
    public String getSaveData() {
        return this.toString();
    }
}

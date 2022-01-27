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

    public String getName() {return taskName;}

    public String getGenericTaskName() {
        return String.format("[%s] %s", done ? "X" : "?", taskName);
    }

    /**
     * Overrides the string representation of the task to just the task name.
     * @return The task name.
     */
    @Override
    public String toString() {
        return "[T]" + getGenericTaskName();
    }

    public String getStorageData() {
        return toString();
    }
}

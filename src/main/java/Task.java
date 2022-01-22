public class Task {
    private String taskName;
    private boolean done;

    /**
     * Constructor for the Task object.
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

    public String getName() {return this.taskName;}

    public String getGenericTaskName() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.taskName);
    }

    /**
     * Overrides the string representation of the task to just the task name.
     * @return The task name.
     */
    @Override
    public String toString() {
        return "[T]" + this.getGenericTaskName();
    }
}

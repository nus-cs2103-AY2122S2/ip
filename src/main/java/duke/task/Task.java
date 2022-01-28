package duke.task;

/**
 * Abstract class representing a Task
 */
public abstract class Task {
    /** Denotes whether or not this task is done */
    protected boolean isDone;
    /** Description of the task */
    protected String task;

    protected Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    protected Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of whether a task is done or not.
     * @return "[X]" if done, "[ ]"" if undone
     */
    public String statusString() {
        return isDone ? "[X]" : "[ ]";
    }

    public abstract String toFileFormat();

    public abstract String toString();
}

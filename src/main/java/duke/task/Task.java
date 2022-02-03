package duke.task;

/**
 * Task object class.
 * Consists of the task's name and if it is done.
 */
public class Task {
    private final String name;
    private boolean isDone;

    public Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.name = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.name);
    }
}

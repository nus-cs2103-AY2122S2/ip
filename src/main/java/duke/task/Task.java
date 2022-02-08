package duke.task;

/**
 * Task object class.
 * Consists of the task's name and if it is done.
 */
public class Task {
    private final String name;
    private boolean isDone;

    /**
     * Task constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     */
    public Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }

    /**
     * Task constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     * @param isDone A boolean parameter to capture if this task is done or not done.
     */
    public Task(String taskName, boolean isDone) {
        this.name = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.name;
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

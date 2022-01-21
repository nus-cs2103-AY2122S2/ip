/**
 * The type Task.
 */
abstract class Task {

    /**
     * The task name.
     */
    protected String taskName;
    /**
     * The variable indicates if the given task has been marked..
     */
    protected boolean isMarked;

    /**
     * Instantiates a new Task.
     *
     * @param taskName the task name
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    /**
     * Toggle marked.
     */
    public void toggleMarked() {
        this.isMarked = !this.isMarked;
    }

    /**
     * Gets task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets marked status.
     *
     * @return the marked status
     */
    public String getMarkedStatus() {
        return isMarked ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] " + this.getTaskName(), this.getMarkedStatus());
    }
}

package duke.task;

public abstract class Task {

    /** The name/description of the `Task`. */
    protected String taskName;
    /** Indicates if the `Task` has been completed. */
    protected boolean isMarked;

    /**
     * Instantiates a new Task.
     *
     * @param taskName the task name/description
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    /**
     * Sets {@link #isMarked} according to whether the input equals to "mark".
     *
     * @param status indicating if the `Task` needs to be marked or unmarked
     */
    public void setMarked(String status) {
        this.isMarked = status.equals("mark");
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsMarked() {
        return isMarked;
    }

    private String getMarkedStatus() {
        return isMarked ? "X" : " ";
    }

    /**
     * Returns the String representation of a `Task`.
     *
     * @return the String representation of a `Task`
     */
    @Override
    public String toString() {
        return String.format("[%s] " + this.getTaskName(), this.getMarkedStatus());
    }

    /**
     * Returns the String representation of a `Task` that is written to the local data file.
     *
     * @return the String representation of a `Task` that is written to the local data file
     */
    public abstract String toFile();
}

/**
 * Represent the task user want to do.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if task is done, else empty.
     *
     * @return "X" if task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the task that is done to true.
     *
     */
    public void setChecked() {
        isDone = true;
    }

    /**
     * Sets the task that is not done to false.
     *
     */
    public void setUnchecked() {
        isDone = false;
    }

    abstract public String saveToFileString();

    /**
     * The String representation of the Task.
     *
     * @return the status and description of the task.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

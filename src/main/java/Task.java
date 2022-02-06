/**
 * Task class acts as a format for other Task classes.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class, set isDone to false by default.
     *
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of if the task is done.
     *
     * @return "X" if task is done.
     *         " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the current task to be done.
     */
    public void setMark() {
        isDone = true;
    }

    /**
     * Sets the current task to be not done.
     */
    public void setUnmark() {
        isDone = false;
    }

    public abstract String whatType();

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

package task;

/** Abstract class that distinguishes between different Tasks.
 *  Holds a description and the status of whether a task is done or not done.
 */
abstract public class Task {
    protected String details;
    protected boolean isDone;

    Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    /**
     * Returns character symbol to represent a completed or not completed task.
     * @return String "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    /**
     * Sets task status as not completed.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Sets task status as completed.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Returns character symbol that represents task type.
     * @return String 'D', 'E' or 'T'.
     */
    abstract public String symbol();

    /**
     * Returns task details along with full string representation of the date if any.
     * @return details of the task.
     */
    abstract public String displayTime();

    /**
     * Returns details of the task.
     * @return String details of the task.
     */
    @Override
    public String toString() {
        return details;
    }
}

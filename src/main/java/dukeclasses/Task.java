package dukeclasses;

/**
 * Represents a Task item which needs to be completed. It can be marked as done or unmarked as not done.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Class constructor to instantiate an instance of Task.
     *
     * @param description String that describes the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Sets the current instance of Task as done (if boolean is true) or not done (if boolean is false).
     *
     * @param isDone Boolean that indicate whether task is done or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the current instance of Task(true if done or false if not done).
     *
     * @return Boolean that indicate whether task is done or not.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the current instance of Task.
     *
     * @return String that describes the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns description of the current status of the task(its description and whether it is marked or not).
     * Identity used in taskList.
     *
     * @return String that identify the task
     */
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", description);
        } else {
            return String.format("[ ] %s", description);
        }
    }

}

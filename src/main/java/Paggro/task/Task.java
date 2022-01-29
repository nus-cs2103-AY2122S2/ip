package paggro.task;

/**
 * This class encapsulates a Task object representing a task as described by the user.
 */
public abstract class Task {
    /** The description of the Task object. */
    public String description;
    /** The boolean indicating if the task is complete. */
    public boolean isDone;

    /**
     * Default constructor of Task.
     * @param des String description of Task object.
     */
    public Task(String des) {
        description = des;
        isDone = false;
    }

    /**
     * Constructor of Task specifying if it is complete.
     * @param des String description of Task object.
     * @param isDone A boolean indicating if the task is complete.
     */
    public Task(String des, boolean isDone) {
        description = des;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    /**
     * Parses the task into a string formatted to be saved to storage.
     * @return String to be saved to storage.
     */
    public abstract String parseTask();
}

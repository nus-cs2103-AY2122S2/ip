package duke.task;

/**
 * An abstraction of tasks.
 */
public abstract class Task {

    // The variables that are common among all tasks.
    String activity;
    boolean status = false;
    String type = " ";

    /**
     * Constructor that contains parameters for all types of tasks.
     * @param activity the activity of the task.
     * @param type the type of task, T, D or E.
     */
    public Task(String activity, String type) {
        this.activity = activity;
        this.type = type;
    }

    /**
     *
     * Sets the status of the task.
     * @param status the status of the task, false for incomplete and true for completed.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Formats the task into a string for the user to read.
     * @return the string to be printed.
     */
    public abstract String printTask();

    /**
     * Converts the task into a string to be stored onto disk.
     * @return the string representation of the task to be stored onto disk.
     */
    @Override
    public String toString() {
        return "";
    }

}

package duke.task;

/**
 * Task is a parent class that represents the different tasks in the list of items that the user noted down.
 */
public class Task {

    private boolean status;
    private String details;
    private int id;

    /**
     * Task constructor.
     * @param details the details of the task.
     */
    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    /**
     * Sets the status of the task to true to mark that it has been completed.
     */
    public void mark() {
        this.status = true;
    }

    /**
     * Sets the status of the task to false to mark that it hass not been completed.
     */
    public void unmark() {
        this.status = false;
    }

    /**
     * Passes the Task object out in readable format.
     * @return the readable string giving the Tasks's details.
     */
    @Override
    public String toString() {
        String out = "";
        if (status) {
            out = " | 1 | " + details;
        } else {
            out = " | 0 | " + details;
        }
        return out;
    }

}

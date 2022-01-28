package duke.task;

/**
 * Task is a parent class that represents the different tasks in the list of items that the user noted down.
 */
public class Task {

    private boolean isComplete;
    private String details;
    private int id;

    /**
     * Task constructor.
     * @param details the details of the task.
     */
    public Task(String details) {
        this.details = details;
        this.isComplete = false;
    }

    /**
     * Sets the status of the task to true to mark that it has been completed.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Sets the status of the task to false to mark that it hass not been completed.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Passes the Task object out in readable format.
     * @return the readable string giving the Tasks's details.
     */
    @Override
    public String toString() {
        String out = "";
        if (isComplete) {
            out = " | 1 | " + details;
        } else {
            out = " | 0 | " + details;
        }
        return out;
    }

    /**
     * Returns the details of the task, including its description and date where possible.
     * @return a string composing the details.
     */
    public String getDetails() {
        return this.details;
    }
}

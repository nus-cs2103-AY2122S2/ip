package van;

/**
 * Abstraction for all the kinds of tasks to be added to the list
 */
public class Task {
    protected String description;
    protected boolean done;

    /**
     * Creates a Task object that encapsulates the description of the
     * details of the task as well as whether the task has been completed.
     *
     * @param description description of the task. e.g. attend a meeting
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Constructs a string detailing the description of the task and its
     * completion status
     *
     * @return String containing details of the task
     */
    public String getStatus() {
        return done ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Returns String for completion status of task for storage purposes
     *
     * @return "1" if task is completed and "0" if not
     */
    public String getCompletion() {
        return done ? "1" : "0";
    }

    /**
     * returns the description of the task for storage purposes
     *
     * @return description of the task
     */
    public String saveStatus() {
        return description;
    }

    /**
     * changes status of the task to be completed
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * changes the status of the task to be incomplete
     */
    public void setunDone() {
        this.done = false;
    }

    /**
     * Returns description of the task
     *
     * @return returns the description of the task
     */
    public String getDescription() {
        return description;
    }
}

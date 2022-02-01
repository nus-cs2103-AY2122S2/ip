package duke.task;

/**
 * Task class
 */
public class Task {

    private String description;
    private boolean done;

    /**
     * Constructor for task object
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Getter for checking task
     * @return boolean for whether task is done
     */
    public boolean getDone() {
        return done;
    }

    /**
     * Setter for to mark task as done
     * @param bool Set task accordingt to boolean
     */
    public void setDone(boolean bool) {
        this.done = bool;
    }
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] "
            + this.description;
    }
}

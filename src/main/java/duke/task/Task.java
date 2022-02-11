package duke.task;

/**
 * Task class
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for task object
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert !this.isDone : "isDone should be false after instantiating the object";
    }

    /**
     * Getter for checking task
     * @return boolean for whether task is done
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Setter for to mark task as done
     * @param bool Set task accordingt to boolean
     */
    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Override the toString method from the object class
     * @return the task description with checkbox depending on isDone
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] "
            + this.description;
    }
}

package Duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get whether it is done or not
     * @return String representation of true/false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * get description
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark task as not done yet
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

}

package Duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Constructor
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get whether it is done or not, mark task done task with X
     * @return String representation of true/false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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

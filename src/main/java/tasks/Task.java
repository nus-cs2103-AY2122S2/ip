package tasks;

/**
 * Represents a task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialize a Task object
     * @param description description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that returns the statusIcon of a task
     * @return A string marked with X if the task has been completed, otherwise ""
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method that returns the statusNumber of a task
     * @return A string marked with 1 if the task has been completed, other wise 0
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    /**
     * Method that marks a task as done
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Method that marks a task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Method that returns the description of the task with the storage file format
     * @return Description of task with the storage file format
     */
    public String toFileFormat() {
        return "," + getStatusNumber() + "," + this.description;
    }


    /**
     * Method that returns the description of the task with the correct display format
     * @return Description of the task with the correct display format
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Getter method to return the description of a task
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }




}


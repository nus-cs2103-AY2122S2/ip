package duke.tasks;

/**
 * Represents the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for class Task.
     *
     * @param description description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns the status icon of the task.
     * @return the status icon of the task, [X] means done, [ ] means not yet completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Returns the detail of the task, to be overridden in its subclasses.
     * @return the detail of the task.
     */
    public String getDetail() {
        return "";
    }
    @Override
    public String toString(){
        return this.getStatusIcon() + this.description;
    }
}

package Duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Costructor method for Task.
     *
     * @param description description of a task to be added.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks status of task.
     *
     * @return 1 if it is done, 0 if not done.
     */
    public char isDone() {
        return this.isDone ? '1' : '0';
    }

    /**
     * Marks task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Unmark a task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets status icon of a task.
     *
     * @return X is it is done, blank if it is not done,
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a description of task.
     * 
     * @return description of task with status icon.
     */
    public String toString() {
        return "[" +getStatusIcon()+ "] " + this.description;
    }

}
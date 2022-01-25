public class Task {
    // attributes
    protected String description;
    protected boolean isDone;

    // constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String to show if a task is done.
     * If the task is done, it will return X
     * Else, it will return a whitespace
     * @return  X or whitespace depending on whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a String of the description of the task
     * @return  description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the isDone attribute to true
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone attribute to false
     */
    public void setNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
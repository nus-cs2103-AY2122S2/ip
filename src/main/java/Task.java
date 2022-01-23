public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of this Task
     *
     * @return "X" if this Task is done, else returns " "
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this Task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of this Task object
     *
     * @return the string representation of this Task object
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the save format in String of this Task object
     * @return A String for the save format of this Task object
     */
    public String getSaveFormat() {
        return this.description;
    }
}
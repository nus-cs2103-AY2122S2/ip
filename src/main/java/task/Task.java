package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of this task.Task
     *
     * @return "X" if this task.Task is done, else returns " "
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task.Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task.Task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of this task.Task object
     *
     * @return the string representation of this task.Task object
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the save format in String of this task.Task object
     * @return A String for the save format of this task.Task object
     */
    public String getSaveFormat() {
        return this.description;
    }
}
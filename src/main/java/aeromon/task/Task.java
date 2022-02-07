package aeromon.task;

/**
 * Task class handles the different type of tasks.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs the Task object.
     * @param description the task name.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the respective task.
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Naisu! You've completed: \n" + this);
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
        System.out.println("OI! What happened to completing: \n" + this);
    }

    /**
     * Gets the description of the task.
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }

    /**
     * Converts the Task object to the output format that is saved in the file.
     * @return the output String.
     */
    public String toOutputFormat() {
        int status = this.isDone ? 1 : 0;
        return String.format("%d / %s", status, this.description);
    }
}
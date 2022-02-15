package aeromon.task;

/**
 * Task class handles the different type of tasks.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    private static final String TASK_STRING_FORMAT = "[%1$s] %2$s";
    private static final String TASK_OUTPUT_FORMAT = "%d / %s";
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

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
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
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
        return String.format(TASK_STRING_FORMAT, getStatusIcon(), description);
    }

    /**
     * Converts the Task object to the output format that is saved in the file.
     * @return the output String.
     */
    public String toOutputFormat() {
        int status = this.isDone ? 1 : 0;
        return String.format(TASK_OUTPUT_FORMAT, status, this.description);
    }
}
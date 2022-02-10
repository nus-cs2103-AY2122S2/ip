package duke.task;

/**
 * Represents a task
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets completion status of task
     *
     * @return "X" if task is done, otherwise " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets description of task
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as done
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone
     */
    public void unmarkTaskDone() {
        this.isDone = false;
    }

    /**
     * Gets format to display task on file
     *
     * @return format to display task on file
     */
    public String getFileFormat() {
        return String.format(" | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Gets general format to display task
     *
     * @return general format to display task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
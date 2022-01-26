package Commands;

/**
 * Represents a task to be done by the user. It contains a description of the task, type of task and mark status.
 */
public class Task  {

    private String description;
    private String type;
    private boolean isDone;

    /**
     * Constructs a Task object that is initially unmarked.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns type of Task
     *
     * @return character that represents type of Task.
     */
    public String getType() { return this.type; }

    /**
     * Returns description of Task
     *
     * @return description of Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns mark status of task which represents whether the user has completed the task.
     *
     * @return "X" if task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns time of Task
     *
     * @return time of Task
     */
    public String getTime() { return ""; };

    /**
     * Returns date of Task
     *
     * @return date of Task
     */
    public String getDate() { return ""; };

    /**
     * Marks Task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as uncompleted.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted String describing the Task.
     *
     * @return formatted String describing the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
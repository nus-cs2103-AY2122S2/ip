package duke.task;

/**
 * Represents a general task which the user has inserted.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    /**
     * Generates a Task Instance.
     * @param type The type of task (Todo, Event, Deadline)
     * @param isDone Mark if the task is done
     * @param description The description of the task
     */
    public Task(TaskType type, boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = type;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Generates a string which represents the task for disk storage. The format is as follows:
     * <br>
     * TaskType | isDone | Task Description
     * <br>
     * Note that isDone will be "1" if its is marked as done and "0" otherwise.
     *
     * @return String representation of the task for disk storage.
     */
    public String writeToFile() {
        String isDone = this.isDone ? "1" : "0";
        return String.join(" | ", taskType.toString(), isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", taskType.toString(), getStatusIcon(), description);
    }
}

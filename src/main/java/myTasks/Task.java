package mytasks;

/**
 * Task is the parent class of Deadline, Event and Todo. Task contains information such as the task itself and whether
 * it has been completed.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task with the task description. Set the completion of the task to not done as default .
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Visual icon to show the user if the task has been completed
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Indicate that the task is completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicate that the task has not been completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get description for the task.
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get status of whether the task has been completed.
     * @return status of whether the task has been completed.
     */
    public boolean getIsDone() {
        return isDone;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

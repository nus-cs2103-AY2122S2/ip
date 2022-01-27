package duke;

/**
 * Represents a task
 */
public abstract class Task {
    private String description;
    private boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    Task(String description, Boolean completed) {
        this.description = description;
        this.isCompleted = completed;
    }

    /**
     * Obtains the description of the task
     * @return String corresponding to the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtains the completion status of the task
     * @return Boolean corresponding to whether the task is completed
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Marks the tasks as completed
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Obtains the type of <code>Task</code>
     * @return String representing the type of <code>Task</code>
     */
    public String getType() {
        return "Task";
    }

    /**
     * Updates the description of a task
     * @param description New description of the task
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    /**
     * Representation of a <code>Task</code> object
     * @return String representing the <code>Task</code> object
     */
    @Override
    public String toString() {
        String taskString = "";
        if (this.isCompleted) {
            taskString += "[X] ";
        } else {
            taskString += "[ ] ";
        }
        taskString += this.description;
        return taskString;
    }
}

package duke;

/**
 * Represents a task
 */
public abstract class Task {
    private final String description;
    private boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    Task(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    /**
     * Obtain the description of the task
     * @return String corresponding to the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtain the completion status of the task
     * @return Boolean corresponding to whether the task is completed
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Marking the tasks as completed
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marking the task as not completed
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Obtain the type of <code>Task</code>
     * @return String representing the type of <code>Task</code>
     */
    public String getType() {
        return "Task";
    }

    /**
     * Representation of a <code>Task</code> object
     * @return String representing the <code>Task</code> object
     */
    @Override
    public String toString() {
        String taskString = "";
        if (this.completed) {
            taskString += "[X] ";
        } else {
            taskString += "[ ] ";
        }
        taskString += this.description;
        return taskString;
    }
}
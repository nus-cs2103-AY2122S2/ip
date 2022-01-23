package duke.task;

/**
 * Represents the Tasks a user could create. A <code> Task </code> object would correspond to a task
 * inputted by a user either a Todo, Deadline or Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description information about the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     * @return a string representation of the task, consisting of its description and whether its done or not.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

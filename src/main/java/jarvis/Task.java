package jarvis;

/**
 * Represents a task object.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a Task object with a description of the task and sets it to not done by default.
     *
     * @param description description of the Task to be done
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the Task
     *
     * @return status of the Task
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : "-"; //mark done tasks with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * This method is used to format a Task object into a String which can then be stored in the text file.
     *
     * @return String This returns the String which details the description and status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

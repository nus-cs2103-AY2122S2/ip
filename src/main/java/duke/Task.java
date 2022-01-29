package duke;

/**
 * The Task class.
 *
 * @author Jet Tan
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a new instance of Task.
     * By default, the isDone status is set to false.
     *
     * @param description The description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string containing done status and description of a task.
     *
     * @return string containing done status and description of a task
     */
    @Override
    public String toString() {
        String string;
        if (this.isDone) {
            string = "[X] " + this.description;
        } else {
            string = "[ ] " + this.description;
        }
        return string;
    }
}
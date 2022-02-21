package seedu.task;

/**
 * Contains the basic fields for a generic task.
 * Provides methods to retrieve the basic details of a task object and
 * to update the boolean value for whether a task has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of a task object.
     * Sets isDone as false by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "ToDo->ToDo: Description for To Do task cannot be null.";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates an instance of a task object.
     * Sets isDone based on the argument passed in.
     *
     * @param description Description of the task.
     * @param isDone Boolean value of whether the task is marked or unmarked.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "ToDo->ToDo: Description for To Do task cannot be null.";

        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the done status of the task.
     *
     * @return Returns string containing status of whether the task is done.
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the task description stored.
     *
     * @return Returns string containing description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifies the task status as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Modifies the task status as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }


    /**
     * Formats the description of the task to be written to a file.
     *
     * @return String format of task description.
     */
    public String toWrite() {
        String marked = "[" + (this.getStatusIcon()) + "]";
        String output = marked + " " + this.getDescription();
        return output;
    }

    /**
     * Overrides toString() method to print the task in a specific format.
     *
     * @return String to be printed in a specified format for the specific task object.
     */
    @Override
    public String toString() {
        String marked = "[" + (this.getStatusIcon()) + "]";
        String output = marked + " " + this.getDescription();
        return output;
    }
}

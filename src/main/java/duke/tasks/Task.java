package duke.tasks;

/**
 * Represents a task object. Parent class of Event, Deadline and To-do objects.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialize a Task object.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that returns the statusIcon of a task.
     * This method will be used in converting the task to UI format.
     * @return A string marked with X if the task has been completed, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method that returns the status number of a task.
     * This method will be used in converting the task to file format.
     * @return A string marked with 1 if the task has been completed, other wise 0.
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    /**
     * Method that marks a task as done.
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Method that marks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Method that converts a task to its storage file format.
     * This format is what you will see in the storage file stored in the data folder.
     * @return A string that represents a tasks in its storage file format.
     */
    public String toFileFormat() {
        return "," + getStatusNumber() + "," + this.description;
    }

    /**
     * Method that converts a task to its UI format.
     * This format is what will be shown to the user on the GUI.
     * @return A string that describes a task with its UI format.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string containing the description of a task.
     * @return String description of a task.
     */
    public String getDescription() {
        return this.description;
    }

}


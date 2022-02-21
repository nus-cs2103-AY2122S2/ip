package duke.task;

/**
 * Task that users input into Duke.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object, including its Done status.
     *
     * @param description description of Task.
     * @param isDone status of Task, whether is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets isDone status.
     *
     * @return isDone status of Task.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }
    /**
     * Returns "X" when a task is done, and " " when a task is yet to be done.
     *
     * @return the status "X" or " ".
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns "1" when a task is done, and "0" when a task is yet to be done.
     *
     * @return the String representation of boolean, "1" or "0".
     */
    public String getBool() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns "T" for todo task, "D" for deadline task, "E" for Event tasks.
     *
     * @return the letter of the Task Type.
     */
    public String getLetter() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        } else {
            return null;
        }
    }

    /**
     * Getter for description.
     *
     * @return task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task data for a task, for Storage to save and load.
     *
     * @return the Task Data in String for Storage to parse and load.
     */
    public String getTaskData() {
        return this.getLetter() + " | " + this.getBool() + " | " + this.description;
    }

    /**
     * @return the String representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

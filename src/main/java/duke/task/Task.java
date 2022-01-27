package duke.task;
/**
 * This class encapsulates a task input by the user.
 */
public abstract class Task {
    private String description; //description of the task.
    private boolean isDone; //boolean condition: when true, task is done. when false, task is not done.

    /**
     * Constructor to create a task object.
     * @param description: Description of task with String datatype.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * This method mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * This method unmark the task as done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     *
     * @return Returns a String of the description of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

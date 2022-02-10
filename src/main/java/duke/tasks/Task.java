package duke.tasks;

/**
 * An abstract class that represents a Task in a TaskList.
 */
public abstract class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructor for a Task.
     * Creates a new Task.
     *
     * @param task   the task description
     * @param isDone whether this task has been done or not
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Abstract method that gets the Command used to add a Task
     *
     * @return a Command in String format
     */
    public abstract String getStringCmd();

    /**
     * Gets the status of this Task.
     *
     * @return String representation of whether a Task is done or not
     */
    public String getIsDone() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Gets the boolean version of the status of this task
     *
     * @return true if it is done, otherwise false
     */
    public boolean isDoneStatus() {
        return this.isDone;
    }

    /**
     * Gets the task's description.
     *
     * @return description of the Task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Marks this Task as being done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as being not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the String version of a Task.
     *
     * @return String-formatted Task
     */
    @Override
    public String toString() {
        return this.getIsDone() + this.getTask();
    }
}

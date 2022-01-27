package duke.task;

import java.io.Serializable;

/**
 * Represents a general Task that can be put into the TaskList.
 */
public abstract class Task implements Serializable {
    private String taskName;
    private boolean status;

    /**
     * A constructor to store information about the task name.
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Marks this Task as complete.
     */
    public void markDone() {
        this.status = true;
    }

    /**
     * Marks this Task as incomplete.
     */
    public void markUndone() {
        this.status = false;
    }

    /**
     * Returns the name of the Task.
     *
     * @return A String representation of the name of the Task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a boolean on whether the Task is complete.
     * @return
     */
    public boolean isComplete() {
        return this.status;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A String representation of the task.
     */
    public abstract String toString();

}

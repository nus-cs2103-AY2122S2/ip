package duke;

/**
 * Task Class
 */

public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor to create an instance of a Task
     *
     * @param taskName name of task
     */

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Method to get the isDone status icon of a task
     *
     * @return X if task is done, blank if task is not done
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method to mark task as done
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to mark task as not done
     */

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Getter method to get task name
     *
     * @return task name
     */

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Return task status icon and the task name
     *
     * @return task status icon and the task name
     */

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskName();
    }
}

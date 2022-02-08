package sana.task;

/**
 * This class represents the tasks Sana will remember
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public abstract class Task {
    /** true when sana.task is done, false otherwise */
    private boolean isDone;

    /** name of the sana.task */
    private String taskName;

    /**
     * Constructor for the sana.task object
     *
     * @param taskName  name of the sana.task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for the sana.task object
     *
     * @param taskName  the sana.task name
     * @param isDone      whether the sana.task is done
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Renames the task
     *
     * @param newName   new name of the task
     */
    public void rename(String newName) {
        this.taskName = newName;
    }

    /**
     * Returns the sana.task name
     *
     * @return taskName
     */
    @Override
    public String toString() {
        return taskName;
    }

    /**
     * Returns whether the sana.task is done
     *
     * @return if task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the sana.task to done or not
     *
     * @param done  sets the sana.task's completion
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Converts sana.task into a string to be stored in mem
     *
     * @return  String representation of the sana.task in mem
     */
    public abstract String taskToMemStr();

    /**
     * Returns the string representation of the sana.task in a tasklist
     *
     * @return  String representation of the sana.task in taskList
     */
    public abstract String toStringFromList();

}

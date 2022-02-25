package duke.tasks;

import java.io.Serializable;

/**
 * Encapsulates a task object
 */
public class Task implements Serializable {

    /**
     * The rank of the task
     */
    int rank;
    /**
     * Description of a task object
     */
    String task;
    /**
     * Completed state of a task
     */
    boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param rank the rank of the task
     * @param task the description of the task
     */
    public Task(int rank, String task) {
        this.rank = rank;
        this.isDone = false;
        this.task = task;
    }

    /**
     * Marks a task as done
     *
     * @param done state to mark the completion status of a task
     */
    public void markDone(boolean done) {
        this.isDone = done;
    }

    /**
     * String representation of a Task
     *
     * @return the string representation of a task
     */
    public String toString() {
        String isDone = this.isDone ? "[X] " : "[ ] ";
        return isDone + task;
    }
}
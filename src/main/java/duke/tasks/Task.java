package duke.tasks;

import java.io.Serializable;

/**
 * Encapsulates a task object
 */
public class Task implements Serializable, KeywordFinder {

    /**
     * Description of a task object
     */
    String taskDescription;
    /**
     * Completed state of a task
     */
    boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param taskDescription the description of the task
     */
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    /**
     * Marks a task as done
     *
     * @param isDone state to mark the completion status of a task
     */
    public void markDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if task description contains specified keyword
     *
     * @param keyword the keyword
     * @return whether or not task description contains keyword
     */
    public boolean matches(String keyword) {
        return this.task.contains(keyword);
    }

    /**
     * String representation of a Task
     *
     * @return the string representation of a task
     */
    public String toString() {
        String state = this.isDone ? "[X] " : "[ ] ";
        return state + taskDescription;
    }
}
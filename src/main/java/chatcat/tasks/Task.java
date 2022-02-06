package chatcat.tasks;

import java.io.Serializable;

/**
 * The default Task class.
 */
public class Task implements Serializable {
    private final String TASK;
    private boolean isDone;

    /**
     * Creates a {@code Task} object using a specified description.
     *
     * @param task the description of this task.
     */
    public Task(String task) {
        this.TASK = task;
        this.isDone = false;
    }

    /**
     * Marks a task {@code Task} as completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Unmarks a task {@code Task} as not completed.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns a string representing status of task {@code Task}.
     *
     * @return string representing status of task {@code Task}.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a true if task contains a keyword, else returns false.
     *
     * @return true if task contains a keyword, else returns false.
     */
    public boolean containsKeyword(String str) {
        return TASK.contains(str);
    }

    /**
     * Returns a representation in string of {@code Task} task.
     *
     * @return a representation in string of {@code Task} task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + TASK;
    }
}

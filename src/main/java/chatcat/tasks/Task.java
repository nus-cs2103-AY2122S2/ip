package chatcat.tasks;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The default Deadline class. Inherited from {@code Task}.
 *
 * @see DateTimeFormatter
 * @see DateTimeFormatterBuilder
 */
public class Task implements Serializable {
    private final String task;
    private boolean isDone;

    /**
     * Creates a {@code Task} object using a specified description.
     *
     * @param task the description of this task.
     */
    public Task(String task) {
        this.task = task;
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
    public void setUnDone() {
        isDone = false;
    }

    /**
     * Returns a string representing status of task {@code Task}.
     *
     * @return string representing status of task {@code Task}.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a representation in string of {@code Task} task.
     *
     * @return a representation in string of {@code Task} task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + task;
    }
}

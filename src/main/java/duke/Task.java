package duke;

import java.time.LocalDateTime;

/**
 * Represents a type of task that is added by user.
 */
public class Task {


    public boolean isDone;
    public String desc;
    public String type;

    /**
     * Instantiates a new Task.
     *
     * @param desc the task description
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        this.type = " ";
    }

    /**
     * Instantiates a new Task.
     *
     * @param desc the task description
     * @param type the task type
     */
    public Task(String desc, String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Instantiates a new Task.
     *
     * @param desc the  task description
     * @param done the indicator of whether task is marked done
     * @param type the task type
     */
    public Task(String desc, boolean done, String type) {
        this.desc = desc;
        this.isDone = done;
        this.type = type;
    }

    /**
     * Gets done.
     *
     * @return the done
     */
    public String getDone() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Mark task.
     *
     * @return a new task but marked
     */
    public Task mark() {
        return new Task(this.desc, true, this.type);
    }

    /**
     * Unmark task.
     *
     * @return a new task but unmarked
     */
    public Task unmark() {
        return new Task(this.desc);
    }

    /**
     * Gets by.
     *
     * @return the deadline
     */
    public LocalDateTime getBy() {
        return null;
    }
}

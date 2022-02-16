package duke.modules;

import java.io.Serializable;

/**
 * Represents a task.
 */
class Task implements Serializable {

    // 1 indicates done and 0 indicates not done
    private int status;
    private final String name;

    /**
     * Constructor for a task.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.status = 0;
        this.name = name;
    }

    /**
     * Returns the status of completion for a task. 1 indicates done and 0 indicates not done.
     *
     * @return An integer representing task completion status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.status = 1;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.status = 0;
    }

    @Override
    public String toString() {
        if (status == 1) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

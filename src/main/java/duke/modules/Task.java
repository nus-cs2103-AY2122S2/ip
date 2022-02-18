package duke.modules;

import java.io.Serializable;

/**
 * Represents a task.
 */
class Task implements Serializable {

    private boolean isCompleted;
    private final String name;

    /**
     * Constructor for a task.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    /**
     * Returns the status of completion for a task.
     *
     * @return A boolean representing task completion status.
     */
    public boolean getIsCompleted() {
        return isCompleted;
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
        this.isCompleted = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (isCompleted == true) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

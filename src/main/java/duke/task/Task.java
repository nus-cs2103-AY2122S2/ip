package duke.task;

/**
 * Represents a task. Meant to be inherited by more specified tasks.
 */

public class Task {
    public String name;
    /**
     * To indicate whether the task has been completed
     */
    boolean isDone;

    /**
     * Initializes a new task.
     *
     * @param name Description or name of the task.
     */

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Prints out a formatted version of the task with its done state.
     *
     * @return Formatted task.
     */

    public String toString() {
        String doneState = isDone ? "X" : " ";
        return String.format("[%s] %s", doneState, this.name);
    }
}

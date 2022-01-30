package duke.task;

/**
 * Represents a task. Meant to be inherited by more specified tasks.
 */

public class Task {
    private String name;
    /**
     * To indicate whether the task has been completed
     */
    private boolean isMarked;

    /**
     * Initializes a new task.
     *
     * @param name Description or name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Marks the task as done.
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Prints out a formatted version of the task with its done state.
     *
     * @return Formatted task.
     */

    public String toString() {
        String doneState = isMarked ? "X" : " ";
        return String.format("[%s] %s", doneState, this.name);
    }
}

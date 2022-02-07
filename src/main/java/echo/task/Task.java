package echo.task;

import echo.command.TodoCommand;

/**
 * Encapsulates a task.
 */
public class Task {
    /** Description of task. */
    protected final String DESCRIPTION;

    /** Status of task. */
    private boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param desc Description of task.
     */
    public Task(String desc) {
        this.DESCRIPTION = desc;
        this.isDone = false;
    }

    /**
     * Marks task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return String representing the status of task.
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * String representation of task for saving.
     *
     * @return String representation of task for saving.
     */
    public String saveFormat() {
        int isDoneInt = isDone ? 1 : 0;
        return String.format("%s | %s", isDoneInt, DESCRIPTION);
    }

    /**
     * String representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), DESCRIPTION);
    }

    /**
     * Checks if instances of Task are equal.
     *
     * @param obj Object.
     *
     * @return If the DESCRIPTION is equal, returns true; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            // Since obj is an instanceof Task, it is safe to type cast
            // Object to Task.
            Task task = (Task) obj;
            return this.DESCRIPTION.equals(task.DESCRIPTION);
        }
        return false;
    }
}

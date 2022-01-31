package duke.tasks;

/**
 * This class represents a task and all its attributes like its name or whether it is completed.
 */

public class Task {
    protected String name;
    protected boolean isCompleted = false;

    /**
     * Construct a task.
     *
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Alternative constructor for Task, to create a task with a preset completion status.
     *
     * @param name
     * @param isCompleted
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Complete the task.
     */
    public void complete() {
        isCompleted = true;
    }

    /**
     * Make the task uncomplete.
     */
    public void removeCompletedStatus() {
        isCompleted = false;
    }

    /**
     * Returns the file string representation of this task.
     *
     * @return
     */
    public String toFileString() {
        return "T : " + (isCompleted ? "1 : " : "0 : ") + name;
    }

    /**
     * Returns a boolean representing if the task name has the given expr.
     *
     * @param expr keyword to match in the task name.
     * @return true if keyword found in task name, false otherwise.
     */
    public boolean nameMatchesKeyword(String expr) {
        return name.contains(expr);
    }

    /**
     * Overriden toString function. Prints the completion status and the task name in this format:
     * "[ ] taskname" if incomplete, "[X] taskname" if complete.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

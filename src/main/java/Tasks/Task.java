package Tasks;

/**
 * This class represents a task and all its attributes like its name or whether it is completed.
 */

public class Task {
    protected String name;
    protected boolean completed = false;

    /**
     * Construct a task.
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Complete the task.
     */
    public void complete() {
        completed = true;
    }

    /**
     * Make the task uncomplete.
     */
    public void removeCompletedStatus() {
        completed = false;
    }

    /**
     * Overriden toString function. Prints the completion status and the task name in this format:
     * "[ ] taskname" if incomplete, "[X] taskname" if complete.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

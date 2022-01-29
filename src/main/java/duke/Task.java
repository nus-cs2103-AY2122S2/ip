package duke;

/**
 * Represents a task
 *
 * @author sibinhho99-nus
 */
public class Task {
    boolean isDone = false;
    String name = "";

    /** Constructor
     */
    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    /** Returns String representation of the Task
     * @param
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ",name);
    }
}

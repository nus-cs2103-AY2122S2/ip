package tsohg;

/**
 * Represents the todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructor of the class
     * @param name Name of the task.
     */
    public TodoTask(String name, boolean priority) {
        super(name, priority);
    }

    /**
     * Returns the string representation of the object.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return String.format("[T][%s][%s] %s", getPriority(), getStatusIcon(), name);
    }

    /**
     * Returns the stored data representation of the object.
     * @return The stored data representation.
     */
    @Override
    public String toStore() {
        return String.format("T | %d | %d | %s", isHighPriority ? 1 : 0, isDone ? 1 : 0, name);
    }
}

package tsohg;

/**
 * Represents the todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructor of the class
     * @param name Name of the task.
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the object.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), name);
    }

    /**
     * Returns the stored data representation of the object.
     * @return The stored data representation.
     */
    @Override
    public String toStore() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.name);
    }
}

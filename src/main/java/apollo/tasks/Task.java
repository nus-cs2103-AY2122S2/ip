package apollo.tasks;

/**
 * Superclass for all {@code Task} objects.
 * Implements {@code java.io.Serializable} interface.
 */
public class Task implements java.io.Serializable {
    private boolean isDone;
    private final String description;
    private final Type type;

    /**
     * Enum of types of tasks.
     */
    public enum Type {
        TODO, DEADLINE, EVENT;

        /**
         * Prints initial of enum types.
         *
         * @return String representation of enum object.
         */
        @Override
        public String toString() {
            return this.name().substring(0,1);
        }
    }

    /**
     * Constructor for {@code Task}.
     *
     * @param description Of task.
     * @param type Enum type of task.
     */
    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns string representation of the status icon.
     *
     * @return String representation of status.
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks task as done or not done.
     *
     * @param isDone Boolean if task is done.
     */
    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns default string representation of {@code Task} objects.
     *
     * @return String representation of object.
     */
    public String toString() {
        return String.format("[%s][%s] %s", type, this.getStatus(), this.description);
    }
}

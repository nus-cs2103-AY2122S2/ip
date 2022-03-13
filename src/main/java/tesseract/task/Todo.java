package tesseract.task;

/**
 * Represent a todo, a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Todo extends Task {
    private static final String TASK_SYMBOL = "T@";
    private static final String TASK_STRING = "[T]";
    /**
     * Create a todo.
     *
     * @param description A description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Convert the todo to a string in the format of storage memory.
     *
     * @return Memory representation of the todo.
     */
    @Override
    public String toMemoryString() {
        String output = TASK_SYMBOL + super.toMemoryString();
        return archiveString(output);
    }

    /**
     * Convert the todo to a string for UI.
     *
     * @return The UI representation of the todo.
     */
    @Override
    public String toString() {
        return TASK_STRING + super.toString();
    }
}

package duke.task;

/**
 * Represents a todo task with only description of it.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task with its description.
     * 
     * @param description description of todo task.
     */
    public Todo(String description) {
        super(description, 'T');
    }

    /**
     * Returns a formatted string of a Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.getStatusIcon(), super.getDescription());
    }
}

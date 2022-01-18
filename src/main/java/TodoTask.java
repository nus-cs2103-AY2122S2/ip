/**
 * A todo task.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new todo task from the description
     *
     * @param description description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return the string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

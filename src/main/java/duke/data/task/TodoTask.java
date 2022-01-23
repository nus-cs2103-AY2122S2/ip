package duke.data.task;

/**
 * A todo task.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new todo task from the description, completion status and id.
     *
     * @param description description of the task.
     * @param done completion status of the task.
     * @param id id of the task.
     */
    public TodoTask(String description, boolean done, String id) {
        super(description, done, id);
    }

    /**
     * Constructs a new todo task from the description.
     *
     * @param description description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

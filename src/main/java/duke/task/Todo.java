package duke.task;

/**
 * Represents a task (with no date/time) to be done.
 */
public class Todo extends Task {

    protected String type;

    /**
     * Class Constructor.
     *
     * @param description The description of a todo task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns the string representation of a todo task.
     *
     * @return String representation of the status and description of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the type of the todo task.
     *
     * @return "T", which is a short notation of a todo type.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Updates the todo task with the given description.
     *
     * @param description The description to be updated.
     * @return The updated task.
     */
    @Override
    public Task updateTask(String description) {
        setDescription(description);
        return this;
    }
}


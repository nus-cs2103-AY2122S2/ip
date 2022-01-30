package duke.task;

/**
 * Todo class represents tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Creates a todo task with the given data.
     *
     * @param description Description of the task to be created.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates a todo task with the given data.
     *
     * @param description Description of the task to be created.
     * @param isDone Status of the task to be created.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

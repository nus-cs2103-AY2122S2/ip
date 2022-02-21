package tasks;

/**
 * Represents work that needs to be done with no time constraints.
 */
public class Todo extends Task {
    /**
     * Returns a Todo object that contains the details of the
     * work that needs to be done with no time constraints.
     *
     * @param description describes the work that needs to be done.
     */
    public Todo(String description) {
        super(description);
    }

    private Todo(String description, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
    }

    /**
     * Duplicates the current Todo task.
     *
     * @return A duplicate of the current Todo task.
     */
    @Override
    public Todo clone() {
        return new Todo(super.description, super.isDone, super.priority);
    }

    /**
     * Describes the current Todo task with a prefix indicating its task type.
     *
     * @return A string representation of the current Todo task's description
     * and completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

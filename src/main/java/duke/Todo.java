package duke;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs an instance of the Todo class, which is unmarked by default.
     * @param description A string representing the task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construct an instance of a marked or unmarked todo task.
     *
     * @param description A string representing the task description.
     * @param isDone A boolean representing whether the task is marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Task mark() {
        return new Todo(this.getDescription(), true);
    }

    @Override
    public Task unmark() {
        return new Todo(this.getDescription(), false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

/**
 * Represents a Todo task without a specific deadline or time.
 */
public class Todo extends Task {

    /**
     * Creates a new instance of a Todo.
     * Assumes the Task is not done yet.
     *
     * @param name The content of the Task.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Creates a new instance of a Todo.
     *
     * @param name The content of the task.
     * @param isDone Whether the Task is done yet.
     */
    public Todo(String name, boolean isDone) {
        super(name, "T", isDone);
    }
}

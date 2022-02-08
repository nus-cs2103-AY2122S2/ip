package duke.task;

/** Represents a Todo task without specific deadline or time */
public class Todo extends Task {

    /**
     * Creates a new instance of a Todo.
     * Assumes the task is not done yet.
     *
     * @param name The content of the task.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Creates a new instance of a Todo.
     *
     * @param name The content of the task.
     * @param isDone Whether the task is done yet.
     */
    public Todo(String name, boolean isDone) {
        super(name, "T", isDone);
    }
}

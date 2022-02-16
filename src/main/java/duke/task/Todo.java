package duke.task;

/**
 * A class that represents a todo.
 */
public class Todo extends Task {
    /**
     * Creates an Todo instance with a title.
     *
     * @param title The title of the todo.
     */
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

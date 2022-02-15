package duke.task;

/**
 * Represents an todo task made by the user. A <code>Todo</code> object stores
 * the description of the task. The task can be mark as done
 * after the user complete the specific task.
 */
public class Todo extends Task {
    /**
     * Creates an instance of a Todo object.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates an instance of a Todo object.
     *
     * @param description the description of the todo task.
     * @param isMark whether the todo task is mark.
     */
    public Todo(String description, boolean isMark) {
        super(description, isMark);
    }

    /**
     * Returns the visual description of the todo task.
     *
     * @return description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the data of the todo task.
     *
     * @return data of the todo task.
     */
    @Override
    public String toData() {
        return "T|" + super.toData();
    }
}

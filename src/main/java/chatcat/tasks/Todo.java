package chatcat.tasks;

/**
 * The default Todo class. Inherited from {@code Task}.
 *
 * @see Task
 */
public class Todo extends Task {

    /**
     * Creates a {@code Todo} object using a specified description.
     *
     * @param todo the description of this task.
     */
    public Todo(String todo) {
        super(todo);
    }

    /**
     * Returns a representation in string of {@code Todo} task.
     *
     * @return a representation in string of {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

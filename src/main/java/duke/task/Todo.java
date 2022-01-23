package duke.task;

/**
 * Represents the todo task a user would create. A <code>Todo</code> object is a subclass of the Task class
 * and corresponds to a todo inputted by a user.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     * @param description information about the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo.
     * @return a string representation of the todo, consisting of its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

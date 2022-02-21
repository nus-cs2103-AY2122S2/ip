package duke;

/**
 * Todo class for storing event tasks
 */
public class Todo extends Task {

    private static String TODO_EMPTY_DESCRIPTION = "TODO task must not have an empty description!";

    /**
     * Initialise todo task with a description (task name)
     */
    public Todo(String description) {
        super(description, Type.TODO);

        assert description != "" : TODO_EMPTY_DESCRIPTION;
    }

    /**
     * Return string representation of the todo task
     * e.g. [T][X] Sample event task
     *
     * @return String representation of todo task:
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

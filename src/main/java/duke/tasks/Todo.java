package duke.tasks;

/**
 * Represents a <code>Todo</code> Task.
 */

public class Todo extends Task {

    /**
     * Constructor which takes in the name of the Todo task.
     * @param name
     */
    public Todo(String name) {
        super(name, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

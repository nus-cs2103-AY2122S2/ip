package duke.task;

/**
 * Represents a todo task.
 *
 * @author Peter
 */
public class Todo extends Task {
    /**
     * Constructor for a todo task.
     *
     * @param description Description associated with the todo task.
     * @param isMarked    Boolean flag of whether the todo task is done.
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * Converts the todo task to a form legible by the storage.
     *
     * @return Data representation of the todo task.
     */
    @Override
    public String toData() {
        return "T | " + this.isMarked + " | " + this.description;
    }

    /**
     * Creates a copy of the todo task.
     *
     * @return Copy of the todo task.
     */
    @Override
    public Todo copy() {
        return new Todo(this.description, this.isMarked);
    }

    /**
     * Reformats and returns string representation of todo task.
     *
     * @return Reformatted string representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

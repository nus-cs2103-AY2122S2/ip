package duke.task;

/**
 * Represents a to do task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo object.
     *
     * @param description the description of the tasl
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to do task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + super.description;
    }
}

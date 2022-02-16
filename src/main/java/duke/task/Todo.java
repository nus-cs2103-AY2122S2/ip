package duke.task;

/**
 * Encapsulates a Todo item with only a description.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo task with a specified description.
     *
     * @param description the description of this Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this Todo task.
     *
     * @return the string representation of this Todo task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]").append(super.toString());
        return sb.toString();
    }
}

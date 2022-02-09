package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * A ToDo constructor to initialise a <code>ToDo</code> object. A <code>ToDo</code>
     * corresponds to a task represented by a String.
     * E.g., <code>do project</code>.
     *
     * @param description the description of the task to be done.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * An overloaded ToDo constructor to initialise a <code>ToDo</code> object. A <code>ToDo</code>
     * corresponds to a task represented by a String and a Tag.
     * E.g., <code>do project</code>.
     *
     * @param description the description of the task to be done.
     * @param tag the tag of the task to be added.
     */
    public ToDo(String description, Tag tag) {
        super(description, tag);
    }

    /**
     * Returns the string representation of the <code>ToDo</code> task.
     *
     * @return the string representation of the <code>ToDo</code> task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

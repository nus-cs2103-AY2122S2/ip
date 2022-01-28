package duke.task;

/**
 * Represents a to-do item.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the to-do item as a string which can be saved and loaded as a to-do item again.
     *
     * @return To-do item as a string which can be saved and loaded as a to-do item again.
     */
    @Override
    public String save() {
        return "T | " + super.save()  + System.lineSeparator();
    }

    /**
     * Returns the to-do item as a readable string.
     *
     * @return To-do item as a readable string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package sparrow.logic.task;

import sparrow.model.Priority;
import sparrow.model.Status;

/**
 * Represents a to-do item.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do.
     * @param d The event description.
     * @param s The event status.
     * @param p The event priority.
     */
    public ToDo(String d, Status s, Priority p) {
        super(d, s, p);
    }

    /**
     * Constructs a to-do with unspecified status and priority, which defaults to NOT_DONE and MEDIUM respectively.
     * @param d The event description.
     */
    public ToDo(String d) {
        super(d);
    }

    /**
     * Returns the to-do item as a string which can be saved and loaded as a to-do item again.
     *
     * @return To-do item as a string which can be saved and loaded as a to-do item again.
     */
    @Override
    public String save() {
        return "T | " + super.save() + " | " + savePriority() + "\n";
    }

    /**
     * Returns the to-do item as a readable string.
     *
     * @return To-do item as a readable string.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString() + "\n";
    }
}

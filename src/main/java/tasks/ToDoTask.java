package tasks;

/**
 * This class encapsulates a To-Do Task.
 *
 * This is the most generic task.
 *
 * @author Ong Han Yang
 */
public class ToDoTask extends Task {
    /**
     * Constructs a To Do task.
     *
     * @param desc the description of the task.
     */
    public ToDoTask(String desc) {
        super(desc);
    }

    /**
     * Produces a To Do task.
     *
     * @param desc the description of the task.
     * @return the To Do task.
     */
    public static ToDoTask of(String desc) {
        return new ToDoTask(desc);
    }

    /**
     * Represents the To Do task as a String.
     *
     * @return String representation of a To Do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

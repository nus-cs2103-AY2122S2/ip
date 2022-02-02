package dazz.task;

/**
 * Represent a task that is a todo.
 */
public class Todo extends Task {
    private static final String TYPE = "T";

    /**
     * Constructs a todo task.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task and is completed depending on <code>isDone</code>
     * @param description Description of the todo
     * @param isDone Done or undone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Reformats the task to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === ";
    }

    /**
     * String representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }
}

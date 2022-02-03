package dazz.task;

/**
 * Represent a <code>Task</code> that is a todo.
 */
public class Todo extends Task {
    private static final String TYPE = "T";

    /**
     * Constructs a <code>Todo</code>.
     * @param description Description of this <code>Todo</code>.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a <code>Todo</code> and is completed depending on <code>isDone</code>
     * @param description Description of this <code>Todo</code>.
     * @param isDone Done or undone <code>Todo</code>.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Reformats this <code>Todo</code> to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === ";
    }

    /**
     * String representation of this <code>Todo</code>.
     * @return String representation of this <code>Todo</code>.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }
}

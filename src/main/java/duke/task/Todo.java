package duke.task;

/** Represents a Todo task without specific deadline or time */
public class Todo extends Task {

    /**
     * Creates a new instance of a Todo.
     * Assumes the task is not done yet.
     *
     * @param name The content of the task.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Creates a new instance of a Todo.
     *
     * @param name The content of the task.
     * @param isDone Whether the task is done yet.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a String representation of the Todo.
     * Indicates the task type and whether it has been done.
     *
     * @return A String representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the Todo to be entered in the storage file.
     *
     * @return A String representation of the Todo.
     */
    @Override
    public String convertToFileFormat() {
        if (isDone) {
            return "T | 1 | " + name;
        }
        return "T | 0 | " + name;
    }
}

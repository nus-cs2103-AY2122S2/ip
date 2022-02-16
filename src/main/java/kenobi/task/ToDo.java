package kenobi.task;

/**
 * The ToDo class encapsulates a todo Task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with the given name.
     *
     * @param name The name of the todo.
     * @throws TaskException if the ToDo name is empty.
     */
    public ToDo(String name) throws TaskException {
        super(name);
    }

    /**
     * Returns the type enum corresponding to the Task.
     *
     * @return the type enum corresponding to the Task.
     */
    @Override
    public Type type() {
        return Type.TODO;
    }

    /**
     * Returns a save-friendly string representing the Task.
     *
     * @return a ",.," delimited string representing the Task.
     */
    @Override
    public String toSave() {
        int doneBit = isDone ? 1 : 0;
        return String.format("T,.,%d,.,%s\n", doneBit, name);
    }

    /**
     * Returns a ui-friendly string representing the Task.
     *
     * @return a ui-friendly string representing the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

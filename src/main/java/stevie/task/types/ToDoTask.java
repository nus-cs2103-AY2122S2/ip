package stevie.task.types;

/**
 * A type of task that can be characterised as an to-do. A <code>stevie.task.types.ToDoTask</code> contains
 * a name.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for a todo task.
     *
     * @param name name of the task
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Returns a string representing the task's name.
     *
     * @return the name of the task
     */
    @Override
    public String generateTaskSaveData() {
        return "T|" + (isDone ? "1" : "0") + "|" + name;
    }

    /**
     * Generates a formatted string to be written to a .txt save file.
     *
     * @return a formatted string that can be read to restore the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

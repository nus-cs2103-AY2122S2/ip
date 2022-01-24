package stevie.task;

/**
 * A type of task that can be characterised as an to-do. A <code>stevie.task.ToDoTask</code> contains
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

    @Override
    public String generateTaskSaveData() {
        return "T|" + (isDone ? "1" : "0") + "|" + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

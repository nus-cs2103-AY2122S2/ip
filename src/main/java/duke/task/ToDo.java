package duke.task;

/**
 * A Type of task.
 */
public class ToDo extends Task {

    private static final String TYPE = "T";

    /**
     * Constructs the ToDo task with the given name.
     * @param name given name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructs the ToDo task with the given name is a specified mark value.
     * @param name name of task.
     * @param mark if task is marked.
     */
    public ToDo(String name, boolean mark) {
        super(name);
        this.isMarked = mark;
    }

    /**
     * function to get string representation of the Task to be stored.
     * @return a String representation of the Task to be stored.
     */
    @Override
    public String toStore() {
        return TYPE + " | " + this.markStore() + " | " + this.name;
    }

    /**
     * method that returns the string representation of ToDo.
     * @return a String representation fo ToDo.
     */
    @Override
    public String display() {
        return "[" + TYPE + "] " + "[" + markDisplay() + "] " + this.name;
    }
}

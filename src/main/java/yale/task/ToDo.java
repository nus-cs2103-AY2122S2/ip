package yale.task;

/**
 * Subclass of Task.
 * Basic implementation of Task object.
 */
public class ToDo extends Task {
    /**
     * Constructor method
     * @param name Name of todo.
     * @param isMarked Boolean of whether event is marked.
     */
    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    /**
     * Returns a customised String format of
     * the ToDo object.
     * @return Custom String format of ToDo object.
     */
    @Override
    public String export() {
        return "T " + "| " + (isMarked? 1 : 0) + " | " + this.name;
    }
    /**
     * Returns a customised String.
     * @return Customised String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

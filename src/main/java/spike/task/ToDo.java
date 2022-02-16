package spike.task;

/**
 * Encapsulate information of pure to-do task.
 */
public class ToDo extends Task {
    /**
     * Default constructor.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the representative string for saving in data file.
     */
    @Override
    public String toFileFormat() {
        int status = super.isDone ? 1 : 0;
        return "T" + " | " + status + " | " + super.description;
    }

    /**
     * Returns proper string representation of the task object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

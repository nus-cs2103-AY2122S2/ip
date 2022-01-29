package duke;

/**
 * Represents the ToDo Task object.
 */
public class ToDo extends Task{

    /**
     * Initializes the ToDo object
     * @param description string which describes the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representing the ToDo task.
     * @return String
     */
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}

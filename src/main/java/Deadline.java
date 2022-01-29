package duke;

/**
 * Represents the Deadline Task object.
 */
public class Deadline extends Task {

    /**
     * Initializes the Deadline object
     * @param description string which describes the task.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representing the Deadline task.
     * @return String
     */
    public String toString() {
        return String.format("[D][%s] %s", getStatusIcon(), description);
    }
}

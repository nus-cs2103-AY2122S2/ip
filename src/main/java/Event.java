package duke;

/**
 * Represents the Event Task object.
 */
public class Event extends Task{

    /**
     * Initializes the Event object
     * @param description string which describes the task.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representing the Event task.
     * @return String
     */
    public String toString() {
        return String.format("[E][%s] %s", getStatusIcon(), description);
    }
}

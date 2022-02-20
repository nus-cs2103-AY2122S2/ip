package duke;

/**
 * Represents the Event Task object.
 */
public class Event extends duke.Task{

    /**
     * Initializes the Event object
     * @param description string which describes the task.
     */
    public Event(String description) {
        super(description);
    }

    public Event(String description, String tag) {
        super(description, tag);
    }

    /**
     * Returns a formatted string representing the Event task.
     * @return String
     */

}

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
    public String toString() {
        if (tag != null){
            return String.format("[E] [%s] %s (%s)", getStatusIcon(), description, tag);
        } else {
            return String.format("[E] [%s] %s", getStatusIcon(), description);
        }

    }

}

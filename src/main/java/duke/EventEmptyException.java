package duke;

/**
 * Exception when user attempts to create an event task
 * without providing a task name/description
 */
public class EventEmptyException extends DukeException {
    public EventEmptyException() {
        super("The description of an event cannot be empty.");
    }
}
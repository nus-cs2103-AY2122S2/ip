package duke;

public class EventEmptyException extends DukeException {
    /**
     * Returns an duke.EventEmptyException if an empty event is found
     *
     * @author  Ryan Aidan
     */
    public EventEmptyException() {
        super("The description of an event cannot be empty.");
    }
}
public class EventEmptyException extends DukeException {
    /**
     * Returns an EventEmptyException if an empty event is found
     *
     * @author  Ryan Aidan
     */
    public EventEmptyException() {
        super("The description of an event cannot be empty.");
    }
}

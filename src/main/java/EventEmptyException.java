public class EventEmptyException extends DukeException {
    public EventEmptyException() {
        super("The description of an event cannot be empty.");
    }
}

package duke.Exceptions;

public class EventException extends DukeException {
    public EventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }
}

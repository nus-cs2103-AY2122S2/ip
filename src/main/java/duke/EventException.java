package duke;
//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
public class EventException extends DukeException {
    public EventException(String errorMessage) {
        super(errorMessage);
    }
}

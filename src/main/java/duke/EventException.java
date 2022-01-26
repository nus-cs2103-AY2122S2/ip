package duke;
//solution below adapted from https://stackify.com/specify-handle-exceptions-java/

/**
 * Exception class handling event task exception
 */
public class EventException extends DukeException {
    public EventException(String errorMessage) {
        super(errorMessage);
    }
}

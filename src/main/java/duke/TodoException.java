package duke;
//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
public class TodoException extends DukeException {
    public TodoException(String errorMessage) {
        super(errorMessage);
    }
}

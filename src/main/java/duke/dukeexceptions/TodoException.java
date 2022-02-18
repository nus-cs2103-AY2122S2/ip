package duke.dukeexceptions;

//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
/**
 * Exception class handling todo task exception
 */
public class TodoException extends DukeException {
    public TodoException(String errorMessage) {
        super(errorMessage);
    }
}

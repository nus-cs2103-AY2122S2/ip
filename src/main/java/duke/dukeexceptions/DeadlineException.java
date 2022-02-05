package duke.dukeexceptions;
//solution below adapted from https://stackify.com/specify-handle-exceptions-java/

/**
 * Exception class handling deadline task exception
 */
public class DeadlineException extends DukeException {
    public DeadlineException(String errorMessage) {
        super(errorMessage);
    }
}

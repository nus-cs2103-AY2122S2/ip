package duke.dukeexceptions;
//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
/**
 * Exception class handling all kind of Duke exception
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

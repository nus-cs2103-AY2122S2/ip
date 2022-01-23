package Duke.Exception;

public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super("☹!!! " + errorMsg);
    }
}

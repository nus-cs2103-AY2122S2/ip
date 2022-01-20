package core.exceptions;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    public DukeException() {
        super();
    }
}

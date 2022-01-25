package duke;

public class DukeCommandDoesNotExistException extends DukeException {
    public DukeCommandDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}

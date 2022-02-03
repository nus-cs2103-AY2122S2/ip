package Duke.Exceptions;

public class EmptyMessageException extends DukeException {

    public EmptyMessageException(String exceptionMessage) {
        super("Oops! I'm afraid you missed out a description for the task!");
    }
}

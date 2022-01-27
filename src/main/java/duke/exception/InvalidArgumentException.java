package duke.exception;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException() {
        super("Hmm, I recognized your command but it seems like the arguments are not complete / invalid.");
    }
}

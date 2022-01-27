package core.exceptions;

public class EmptyArgumentException extends DukeException {
    public EmptyArgumentException() {
        super("The find command needs an argument!");
    }
}

package core.exceptions;

/**
 * Exception indicating the absence of arguments for the command.
 */
public class EmptyArgumentException extends DookeException {
    public EmptyArgumentException() {
        super("The find command needs an argument!");
    }
}

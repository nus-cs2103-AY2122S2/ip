package dooke.core.exceptions;

/**
 * Exception indicating the absence of arguments for the command.
 * @author s7manth
 * @version 0.3
 */
public class EmptyArgumentException extends DookeException {
    public EmptyArgumentException() {
        super("The find command needs an argument!");
    }
}

package dooke.core.exceptions;

/**
 * Exception indicating that the command was run without being given a description.
 * @author s7manth
 * @version 0.3
 */
public class NoDescriptionGivenException extends DookeException {
    public NoDescriptionGivenException() {
        super("No description given");
    }
}

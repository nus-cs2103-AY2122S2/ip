package core.exceptions;

/**
 * Exception indicating that the command was run without being given a description.
 */
public class NoDescriptionGivenException extends DookeException {
    public NoDescriptionGivenException() {
        super("No description given");
    }
}

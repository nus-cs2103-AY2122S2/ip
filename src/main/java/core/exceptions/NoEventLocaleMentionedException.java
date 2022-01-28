package core.exceptions;

/**
 * Exception indicating that the event command was run without the locale parameter.
 */
public class NoEventLocaleMentionedException extends DookeException {
    public NoEventLocaleMentionedException() {
        super("No event locale mentioned!");
    }
}

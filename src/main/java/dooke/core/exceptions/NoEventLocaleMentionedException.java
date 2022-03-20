package dooke.core.exceptions;

/**
 * Exception indicating that the event command was run without the locale parameter.
 * @author s7manth
 * @version 0.3
 */
public class NoEventLocaleMentionedException extends DookeException {
    public NoEventLocaleMentionedException() {
        super("No event locale mentioned!");
    }
}

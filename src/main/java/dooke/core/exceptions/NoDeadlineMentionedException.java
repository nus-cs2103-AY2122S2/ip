package dooke.core.exceptions;

/**
 * Exception indicating that the deadline command was run without mentioning a deadline.
 * @author s7manth
 * @version 0.2
 */
public class NoDeadlineMentionedException extends DookeException {
    public NoDeadlineMentionedException() {
        super("No deadline mentioned!");
    }
}

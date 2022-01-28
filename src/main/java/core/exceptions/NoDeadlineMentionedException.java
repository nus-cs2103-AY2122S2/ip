package core.exceptions;

/**
 * Exception indicating that the deadline command was run without mentioning a deadline.
 */
public class NoDeadlineMentionedException extends DookeException {
    public NoDeadlineMentionedException() {
        super("No deadline mentioned!");
    }
}

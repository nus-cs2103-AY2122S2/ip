package core.exceptions;

public class NoDeadlineMentionedException extends DukeException{
    public NoDeadlineMentionedException() {
        super("No deadline mentioned!");
    }
}

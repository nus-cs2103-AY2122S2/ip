package core.exceptions;

public class NoEventLocaleMentionedException extends DukeException {
    public NoEventLocaleMentionedException() {
        super("No event locale mentioned!");
    }
}

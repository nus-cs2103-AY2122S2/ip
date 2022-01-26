package seedu.duke.exceptions;

public class NoDateException extends DukeException {
    public NoDateException() {
        super("Hmmm...did you give me a valid date? Like /by Sunday for deadline or /at for event");
    }
}
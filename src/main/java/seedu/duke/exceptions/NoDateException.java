package seedu.duke.exceptions;

/**
 * Thrown when no date is given to create {@link seedu.duke.task.Deadline} and {@link seedu.duke.task.Event}.
 */
public class NoDateException extends DukeException {
    /**
     * Creates a NoDateException() with a message telling user to add a valid date.
     */
    public NoDateException() {
        super("Hmmm...did you give me a valid date? Like /by Sunday for deadline or /at for event");
    }
}
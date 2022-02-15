package seedu.duke.exceptions;

/**
 * Thrown when a mark or unmark command is executed twice.
 */
public class TaskAlreadyMarkedException extends DukeException {
    public TaskAlreadyMarkedException() {
        super("Oh hmm...seems like I've executed that already");
    }
}

package seedu.duke.exceptions;

public class TaskAlreadyMarkedException extends DukeException {
    public TaskAlreadyMarkedException() {
        super("Oh hmm...seems like I've executed that already");
    }
}

package seedu.duke.exceptions;

import seedu.duke.Duke;

public class TaskAlreadyMarkedException extends DukeException {
    public TaskAlreadyMarkedException() {
        super("Oh hmm...seems like I've executed that already");
    }
}

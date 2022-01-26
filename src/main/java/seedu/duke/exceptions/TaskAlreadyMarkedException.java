package seedu.duke.exceptions;

import seedu.duke.Duke;

public class TaskAlreadyMarkedException extends DukeException {
    public TaskAlreadyMarkedException() {
        super("Oh hmm...you finished it already though...what a trooper!");
    }
}

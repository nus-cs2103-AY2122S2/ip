package seedu.duke.exceptions;

public class NoValidTaskIndexException extends DukeException {
        public NoValidTaskIndexException() {
            super("Hmm the command seems to be wrong - is the task number correct?");
        }
}


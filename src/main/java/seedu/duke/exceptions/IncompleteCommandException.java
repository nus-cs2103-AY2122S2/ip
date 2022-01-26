package seedu.duke.exceptions;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException() {
        super("Hmm the command seems to be wrong - did you tell me the task?");
    }
}


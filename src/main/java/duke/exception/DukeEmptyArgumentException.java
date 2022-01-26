package duke.exception;

import duke.exception.DukeException;

public class DukeEmptyArgumentException extends DukeException {
    @Override
    public String toString() {
        return "arguments shouldn't be empty";
    }
}

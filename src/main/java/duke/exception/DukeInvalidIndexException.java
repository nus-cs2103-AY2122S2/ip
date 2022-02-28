package duke.exception;

import duke.exception.DukeException;

public class DukeInvalidIndexException extends DukeException {
    @Override
    public String toString() {
        return "The index you entered is out of bound";
    }
}

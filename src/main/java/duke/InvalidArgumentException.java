package duke;

import duke.DukeException;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}

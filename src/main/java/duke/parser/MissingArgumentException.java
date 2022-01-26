package duke.parser;

import duke.DukeException;

public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}

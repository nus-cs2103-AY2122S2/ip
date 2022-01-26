package duke.storage;

import duke.DukeException;

public class NoExistingDataException extends DukeException {
    public NoExistingDataException(String errorMessage) {
        super(errorMessage);
    }
}

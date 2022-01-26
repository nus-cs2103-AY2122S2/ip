package duke.exception;

import duke.exception.DukeException;

public class DukeInvalidFileException extends DukeException {
    @Override
    public String toString() {
        return "Something wrong with the file";
    }
}

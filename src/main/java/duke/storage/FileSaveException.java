package duke.storage;

import duke.DukeException;

public class FileSaveException extends DukeException {
    public FileSaveException(String errorMessage) {
        super(errorMessage);
    }
}

package duke.storage;

import duke.DukeException;

/**
 * Thrown when the list of tasks cannot be saved to a file.
 */
public class FileSaveException extends DukeException {
    public FileSaveException(String errorMessage) {
        super(errorMessage);
    }
}

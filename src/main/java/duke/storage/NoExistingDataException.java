package duke.storage;

import duke.DukeException;

/**
 * Thrown when a task list is loaded from a file that does not exist.
 */
public class NoExistingDataException extends DukeException {
    public NoExistingDataException(String errorMessage) {
        super(errorMessage);
    }
}

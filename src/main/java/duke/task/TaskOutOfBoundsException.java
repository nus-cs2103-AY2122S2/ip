package duke.task;

import duke.DukeException;

public class TaskOutOfBoundsException extends DukeException {
    public TaskOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}

package duke.task;

import duke.DukeException;

/**
 * Thrown when a task referred to by index number does not exist in the TaskList.
 */
public class TaskOutOfBoundsException extends DukeException {
    public TaskOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}

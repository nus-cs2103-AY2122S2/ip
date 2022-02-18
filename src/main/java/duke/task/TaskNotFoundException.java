package duke.task;

import duke.main.DukeException;

public final class TaskNotFoundException extends DukeException {


    protected TaskNotFoundException(String message) {
        super(message);
    }

    protected TaskNotFoundException() {
        super("Oops, I'm not able to retrieve this task");
    }
}

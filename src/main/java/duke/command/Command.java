package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    public abstract String executeCommand(TaskList taskList) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

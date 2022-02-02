package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList taskList, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}

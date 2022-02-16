package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}

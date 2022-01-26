package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public abstract class Command {
    private final boolean isExit;

    public Command (boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}

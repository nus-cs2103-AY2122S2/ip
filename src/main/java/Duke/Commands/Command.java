package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

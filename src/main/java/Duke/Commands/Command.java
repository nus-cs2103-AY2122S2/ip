package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Managers.TaskList;
import Duke.Managers.Ui;
import Duke.Managers.Storage;

public abstract class Command {
    protected boolean exit = false;
    public abstract void execute(TaskList tasks, Ui io, Storage storage) throws DukeException;
    public boolean isExit() {
        return exit;
    }
}

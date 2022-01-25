package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException;
    public boolean isBye() {
        return false;
    }
}

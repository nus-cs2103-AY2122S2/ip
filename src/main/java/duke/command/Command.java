package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

abstract public class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}

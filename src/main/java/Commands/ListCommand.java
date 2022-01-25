package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.DukeException;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

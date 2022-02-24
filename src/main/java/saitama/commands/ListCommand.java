package saitama.commands;

import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object that lists the current tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

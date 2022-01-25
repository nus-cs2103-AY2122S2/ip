package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;

/**
 * A Command object that lists the current tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

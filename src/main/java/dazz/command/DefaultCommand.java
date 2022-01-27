package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class DefaultCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showDefault();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class DefaultCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
//        ui.showDefault();
        return ui.messageForDefault();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

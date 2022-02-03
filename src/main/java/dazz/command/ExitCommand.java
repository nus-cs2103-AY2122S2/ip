package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
//        ui.showExit();
        return ui.messageForExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

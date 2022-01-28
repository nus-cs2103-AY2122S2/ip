package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class ExitCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayFarewell();
    }
    public boolean isExit() {
        return true;
    }
}

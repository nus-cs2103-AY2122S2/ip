package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

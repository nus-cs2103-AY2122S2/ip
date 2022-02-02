package Duke.Commands;

import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

public class CommandExit extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

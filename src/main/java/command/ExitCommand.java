package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override public boolean isExit() {
        return true;
    }
}

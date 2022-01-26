package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

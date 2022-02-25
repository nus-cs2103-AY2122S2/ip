package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        storage.updateStorage(tasks);
        ui.showFarewell();
    }
}

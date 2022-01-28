package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
        storage.write(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

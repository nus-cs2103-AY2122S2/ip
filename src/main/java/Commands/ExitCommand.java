package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.DukeException;
import ui.Ui;

public class ExitCommand extends Command{

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTaskList());
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

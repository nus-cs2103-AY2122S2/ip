package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

public class ExitCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTaskList());
        ui.showGoodBye();
    }


    public boolean isExit() {
        return true;
    }
}

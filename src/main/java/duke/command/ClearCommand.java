package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

public class ClearCommand extends Command {
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.clearFile();
        taskList.clear();
        ui.showMessage("ALL TASKS CLEARED");
        return true;
    }
}

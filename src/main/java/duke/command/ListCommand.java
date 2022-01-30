package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.readFromFile();
        ui.showMessage("YOUR TASKS:" + taskList);
        return true;
    }
}

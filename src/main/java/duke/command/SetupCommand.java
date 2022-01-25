package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class SetupCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.populateWith(storage.loadTasksFromFile());
        ui.greet();
    }
}

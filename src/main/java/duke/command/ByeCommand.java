package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        ui.sayBye();
        storage.saveToFile(taskList.formatAsFileData());
    }
    
    @Override
    public boolean isBye() {
        return true;
    }
}
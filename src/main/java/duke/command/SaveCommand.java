package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TasksList;

public class SaveCommand extends Command {

    @Override
    public String execute(TasksList taskList, Storage storage) throws DukeException {
        String response = "The following tasks will be saved: \n" + taskList.list();
        storage.exportData(taskList.toStorageStrings());
        return response;
    }
}

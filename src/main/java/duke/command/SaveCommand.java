package duke.command;

import duke.Storage;
import duke.TasksList;

public class SaveCommand extends Command {

    @Override
    public String execute(TasksList taskList, Storage storage) {
        return storage.exportData(taskList.toStorageStrings(), taskList.list());
    }
}

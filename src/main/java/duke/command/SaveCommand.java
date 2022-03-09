package duke.command;
import duke.TasksList;
import duke.Storage;

public class SaveCommand extends Command {

    @Override
    public String execute(TasksList taskList, Storage storage) {
        return storage.exportData(taskList.toStorageStrings(), taskList.list());
    }
}

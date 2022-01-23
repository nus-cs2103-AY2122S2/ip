package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;

public class DeleteAllCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            taskList.taskSet.clear();
            taskList.tasksArrayList.clear();
            storage.writeFile(taskList);
            ui.deletedAll();
        } catch (Exception e) {
            throw new CortanaException("Something went wrong when attempting to delete all tasks.");
        }
    }
}

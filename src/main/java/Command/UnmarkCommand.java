package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task task = taskList.tasksArrayList.get(index);
            task.markAsUndone();
            storage.writeFile(taskList);
            ui.unmarked(task);
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }
}

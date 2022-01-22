package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.Task;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task task = taskList.tasksArrayList.get(index);
            task.markAsDone();
            storage.writeFile(taskList);
            ui.marked(task);
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }
}

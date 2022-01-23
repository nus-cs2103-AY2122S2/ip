package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        if (taskList.taskSet.contains(task)) {
            throw new CortanaException("Task already exists!");
        } else {
            taskList.taskSet.add(task);
            taskList.tasksArrayList.add(task);
            try {
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new CortanaException(e.getMessage());
            }
            ui.addedTask(taskList, task);
        }
    }
}

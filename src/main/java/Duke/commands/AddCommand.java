package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.tasks.Task;

public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

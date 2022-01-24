package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class AddTaskCommand extends Command {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        ui.showTaskAdded(this.task, taskList);
        storage.saveData(taskList, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

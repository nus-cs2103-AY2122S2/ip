package commands;

import storage.Storage;
import ui.DukeException;
import ui.Ui;
import tasklist.TaskList;
import tasks.Task;

public class DeleteCommand extends Command{
    String taskNum;

    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int currTaskNum = Integer.parseInt(taskNum);
        if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
            Task currTask = tasks.getTaskList().get(currTaskNum - 1);
            tasks.deleteTask(currTaskNum - 1);
            ui.showTaskDeleted(currTask, tasks.getTaskList());
        } else {
            ui.showError("You don't have such task");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

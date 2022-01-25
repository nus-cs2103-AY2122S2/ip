package commands;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
import tasks.Task;

public class UnmarkCommand extends Command{
    String taskNum;

    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int currTaskNum = Integer.parseInt(taskNum);
        if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
            Task currTask = tasks.unmarkTask(currTaskNum);
            ui.showTaskUnmarked(currTask);
        } else {
            ui.showError("You don't have such task");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

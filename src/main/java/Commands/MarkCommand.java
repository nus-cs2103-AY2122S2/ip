package commands;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
import tasks.Task;

public class MarkCommand extends Command{
    String taskNum;

    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int currTaskNum = Integer.parseInt(taskNum);
        if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
            Task currTask = tasks.markTask(currTaskNum);
            ui.showTaskMarked(currTask);
        } else {
            ui.showError("You don't have such task");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

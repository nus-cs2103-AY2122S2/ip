package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.tasks.Task;

public class MarkCommand extends Command{
    String taskNum;

    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int currTaskNum = Integer.parseInt(taskNum);
            if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
                Task currTask = tasks.markTask(currTaskNum);
                ui.showTaskMarked(currTask);
            } else {
                ui.showError("You don't have such task");
            }
        } catch (NumberFormatException e) {
            ui.showError("Error! Please input a task number");
        }

    }


    public boolean isExit() {
        return false;
    }
}

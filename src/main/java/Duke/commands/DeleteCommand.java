package duke.commands;

import duke.storage.Storage;
import duke.ui.DukeException;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command{
    String taskNum;

    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int currTaskNum = Integer.parseInt(taskNum);
            if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
                Task currTask = tasks.getTaskList().get(currTaskNum - 1);
                tasks.deleteTask(currTaskNum - 1);
                ui.showTaskDeleted(currTask, tasks.getTaskList());
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

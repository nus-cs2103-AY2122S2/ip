package saitama.commands;

import saitama.exceptions.InvalidTaskNumberException;
import saitama.storage.Storage;
import saitama.tasks.Task;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object that unmarks a given task.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        Task task = taskList.getTask(taskNumber);
        taskList.unmarkTask(taskNumber);
        return ui.showUnmarkTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package saitama.commands;

import saitama.exceptions.InvalidTaskNumberException;
import saitama.storage.Storage;
import saitama.tasks.Task;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object that deletes a given task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        Task task = taskList.getTask(taskNumber);
        taskList.delete(taskNumber);
        assert !taskList.getTask(taskNumber).equals(task) : "Delete command failed to delete task!";
        return ui.showDeleteTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

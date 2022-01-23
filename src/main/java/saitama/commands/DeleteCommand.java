package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.InvalidTaskNumberException;

/**
 * A Command object that deletes a given task.
 */
public class DeleteCommand extends Command{
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        taskList.delete(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
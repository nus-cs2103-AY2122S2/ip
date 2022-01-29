package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.InvalidTaskNumberException;

/**
 * A Command object that unmarks a given task.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        System.out.println("OK...");
        taskList.unmarkTask(taskNumber);
        System.out.println("The following task has been marked as not done: ");
        System.out.println(taskList.get(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

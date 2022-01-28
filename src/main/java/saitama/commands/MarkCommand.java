package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.InvalidTaskNumberException;

/**
 * A Command object that marks a given task.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        System.out.println("OK...");
        taskList.markTask(taskNumber);
        System.out.println("The following task has been marked as done: ");
        System.out.println(taskList.get(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

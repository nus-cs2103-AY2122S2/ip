package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.InvalidTaskNumberException;
import saitama.tasks.Task;

/**
 * A Command object that deletes a given task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        System.out.println("OK...");
        Task task = taskList.get(taskNumber);
        taskList.delete(taskNumber);
        System.out.println("The following task has been removed from the list: ");
        System.out.println(task);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
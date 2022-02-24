package saitama.commands;

import saitama.exceptions.InvalidTaskNumberException;
import saitama.storage.Storage;
import saitama.tasks.Task;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object that marks a given task.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Initialises the MarkCommand.
     *
     * @param taskNumber The task number to mark
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
        if (taskNumber > taskList.numOfTasks() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        Task task = taskList.getTask(taskNumber);
        taskList.markTask(taskNumber);
        storage.save(taskList.toArrayList());
        return ui.showMarkTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

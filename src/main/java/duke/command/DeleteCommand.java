package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Initialises a DeleteCommand instance.
     *
     * @param index the index of the task to be deleted (starting from 1).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task specified by this DeleteCommand's
     * index from a given task list.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.showTaskDeleted(task, taskList);
        storage.saveData(taskList, ui);
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

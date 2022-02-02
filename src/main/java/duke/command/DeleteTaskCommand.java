package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed deletes a designated task from task list.
 */
public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Class constructor.
     *
     * @param taskIndex index of the task to be deleted.
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Invokes <code>deleteTask</code> method of <code>taskList</code> to delete the designated task.
     * After that, prompts <code>ui</code> to display response messages to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     * @throws DukeException when an exception is thrown in the process of executing this command.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskIndex);

        ui.showMessage("Noted. I have deleted this task:");
        ui.showMessage(deletedTask.toString());
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}

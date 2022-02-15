package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that allows users to delete a task from the task list.
 */
public class Delete extends Command {
    private final Integer taskId;
    private Task deletedTask;

    /**
     * Initialize a Delete Command.
     * @param taskId The index of the task to be deleted.
     */
    public Delete(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns a success string after a successful deletion of a task in the task list.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return A success message after a task is deleted from the task list.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            deletedTask = taskList.getTasks().get(taskId - 1);
            return TaskList.deleteTask(taskId);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a success message after a successful undo of a Delete Command.
     * Throws a DukeException when there errors undoing the command.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Success message after a Delete Command has been undone.
     * @throws DukeException if the program is unable to undo the command.
     */
    @Override
    public String undo(TaskList taskList) throws DukeException {
        return TaskList.addTaskBack(taskId, deletedTask);
    }
}

package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Responsible for deleting a task from the list of tasks in storage
 */
public class DeleteCommand extends Command {

    private int id;

    /**
     * Constructor DeleteCommand.
     * @param i the id of the task that is to be deleted from the list
     * @throws DukeException
     */
    public DeleteCommand(String i) throws DukeException {
        try {
            this.id = Integer.parseInt(i);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid id");
        }
    }

    /**
     * Deletes the requested task from the existing list of tasks, notifies the user
     * of the completion of the task, and updates the user's file.
     * @param tasks holds all the tasks that the user has recorded down.
     * @param ui used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     * @throws DukeException
     */
    @Override
    public String execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removed_task = tasks.deleteTask(this.id);
            storage.saveToFile(tasks.getCurrentTasks(), false);
            return ui.notifyRemovedTaskMessage(removed_task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid id");
        }
    }

}

package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.UiForGUI;
import task.Task;
import task.TaskList;

/**
 * The DeleteCommand class is a type of Command that is used to delete a particular task.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the delete command and deletes the specified task from the TaskList
     * and removes it in the Storage as well.
     *
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     * @throws DukeException If Storage is unable to save the removal the task successfully.
     */
    @Override
    public void execute(TaskList tasks, UiForGUI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.tokenizedCommand[1]) - 1;
        Task t = tasks.getTask(index);
        tasks.removeTask(t);
        storage.updateSavedTasks(t.getSaveFormat(), "");
        ui.printMsg("Noted. I've removed this task:\n  " + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}

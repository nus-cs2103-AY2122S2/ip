package stevie.command;

import stevie.exception.StevieException;
import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * DeleteCommand executes to delete a task from the task list, and save the newly updated task list.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted
     */
    private final int taskIdx;

    /**
     * Constructor for DeleteCommand.
     *
     * @param idx index of task to be deleted
     */
    public DeleteCommand(int idx) {
        taskIdx = idx;
    }

    /**
     * Tries to delete a task from task list at index stored in DeleteCommand's field.
     * Saves the newly updated task list if a task is successfully deleted. Ui outputs
     * a response string to let user know if task is successfully deleted or not.
     *
     * @param tasks task list to make changes on
     * @param storage to handle the saving of data
     * @param ui to pass a response string for output
     * @return false to not terminate the session
     */
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out;
        try {
            out = tasks.delete(taskIdx);
        } catch (StevieException ex) {
            ui.outputMessage(ex.getMessage());
            return false;
        }
        tasks.save(storage);
        ui.outputMessage(out);
        return false;
    }
}

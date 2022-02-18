package stevie.command;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.task.types.Task;
import stevie.undo.UndoHistory;
import stevie.undo.actions.Undo;
import stevie.undo.actions.UndoDelete;

/**
 * DeleteCommand executes to delete a task from the task list, and save the newly updated task list.
 */
public class DeleteCommand extends ChangeCommand<Integer> {
    private static final String message = "I have deleted the following task:\n";
    /**
     * Constructor for DeleteCommand.
     *
     * @param idx index of task to be deleted
     */
    public DeleteCommand(int idx) {
        super(idx);
    }

    /**
     * Tries to delete a task from task list at index stored in DeleteCommand's field.
     * Saves the newly updated task list if a task is successfully deleted. Ui outputs
     * a response string to let user know if task is successfully deleted or not.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @param undoHistory handles the history of the commands executed
     * @return false to not terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        Task task;
        try {
            task = tasks.delete(parameter);
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        undoHistory.addUndo(generateUndo(parameter, task));
        String out = message + task.toString() + "\n" + tasks.getSize();
        tasks.save(storage);
        ui.outputMessage(out);
        return out;
    }

    /**
     * Generate an undo command that is used to reverse this command.
     * @param idx index at which task is deleted
     * @param task task that was deleted
     * @return undo command
     */
    public Undo generateUndo(int idx, Task task) {
        return new UndoDelete(idx, task);
    }
}

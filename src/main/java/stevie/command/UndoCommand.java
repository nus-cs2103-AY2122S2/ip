package stevie.command;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.undo.UndoHistory;
import stevie.undo.actions.Undo;

/**
 * UndoCommand undoes the previous command that made changes to the task list.
 * UndoCommand retrieves the command from UndoHistory to reverse the last
 * executed command.
 */
public class UndoCommand extends Command {
    /**
     * Retrieves and execute undo command from undoHistory.
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @param undoHistory handles the history of the commands executed
     * @return a response string
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        Undo undoAction;
        try {
            undoAction = undoHistory.getUndo();
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        return undoAction.undo(tasks, storage, ui);
    }
}

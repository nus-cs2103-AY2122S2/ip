package stevie.command;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.undo.UndoHistory;
import stevie.undo.actions.Undo;

public class UndoCommand extends Command {
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

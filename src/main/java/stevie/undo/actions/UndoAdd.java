package stevie.undo.actions;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public class UndoAdd implements Undo {
    private static final String message = "Undo previous add command!";
    @Override
    public String undo(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        try {
            tasks.deleteLast();
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        tasks.save(storage);
        ui.outputMessage(message);
        return message;
    }
}

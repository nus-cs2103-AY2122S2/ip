package stevie.undo.actions;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * UndoAdd reverses the last add command by deleting the task added to the bottom of the task list.
 */
public class UndoAdd implements Undo {
    private static final String message = "Undo previous add command!";

    /**
     * Deletes task added to the bottom of the list. Essentially, it reverses the last add command.
     * @param tasks list storing tasks of user
     * @param storage handles the saving of tasks in list
     * @param ui command line interface that interacts with use
     * @return message informing user that add command has been undone
     */
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

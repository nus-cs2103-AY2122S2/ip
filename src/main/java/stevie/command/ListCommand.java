package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.undo.UndoHistory;

/**
 * ListCommand is used to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Ui outputs the list of tasks that exist in the task list.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @param undoHistory handles the history of the commands executed
     * @return false to not terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        String out = tasks.toString();
        ui.outputMessage(out);
        return out;
    }
}

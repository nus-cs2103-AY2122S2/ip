package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * ListCommand is used to list all tasks in the task list.
 */
public class ListCommand extends Command{
    /**
     * Ui outputs the list of tasks that exist in the task list.
     *
     * @param tasks task list to make changes on
     * @param storage to handle the saving of data
     * @param ui to pass a response string for output
     * @return false to not terminate the session
     */
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        ui.outputMessage(tasks.toString());
        return false;
    }
}

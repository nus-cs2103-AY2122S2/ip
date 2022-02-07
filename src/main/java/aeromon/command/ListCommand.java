package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.TaskArrayList;

/**
 * ListCommand class handles the command that lists out the current tasks in the TaskArrayList.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        ui.print("Konnichiwassup! Look at how much work you have to do!");
        ui.print(taskArrayList.getTaskList() + taskArrayList.getTasksStatus());
    }
}

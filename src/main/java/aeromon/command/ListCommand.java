package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.TaskArrayList;

/**
 * ListCommand class handles the command that lists out the current tasks in the TaskArrayList.
 */
public class ListCommand extends Command {

    private static final String LIST_MESSAGE = "Konnichiwassup! Look at how much work you have to do! \n";

    @Override
    public String execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        return LIST_MESSAGE + taskArrayList.getTaskList() + taskArrayList.getTasksStatus();
    }
}

package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ListCommand is a command that handles list command.
 *
 * @author yl-ang
 */
public class ListCommand extends Command {

    private boolean isExit = false;

    /**
     * Executes ListCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     * @return Formatted response from executing command.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        String response = ui.listResponse(taskLst.getTaskLst());
        assert response != null : "List response should not be null";
        return response;
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if command is exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

/**
 *  Executes the exit command for tasks
 *  returns the ui message for Pac response
 */
public class ExitCommand extends Command {

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    /**
     * Changes isExit() to true
     * @return returns boolean value that indicates the program to exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

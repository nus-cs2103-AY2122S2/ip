package jeff.command;

import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * ByeCommand class is a Command that contains instructions
 * to run when user wants to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Say "bye" to the user.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @return goodbye response.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.showBye();
        return response;
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return true to exit the while loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

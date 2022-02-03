package command;

import main.Storage;
import main.TaskList;
import main.TessUi;
import main.TesseractException;

/**
 * Represent a command to exit Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class ExitCommand extends Command {
    /** The specific command keyword */

    ExitCommand(String[] cmdArr) {
        super(cmdArr[0]);
    }

    /**
     * Check if the program is terminated.
     *
     * @return True for ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Execute the command on the system to (update memory and) terminate the program.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        if (storage.isUpdated()) {
            try {
                storage.updateStorage(taskList, taskList.size());
            } catch (TesseractException e) {
                ui.showError(e.getErrMsg());
                ui.admitBug();
            }
        }
        return ui.sayBye();
    }
}

package karen.command;

import karen.Storage;
import karen.Ui;

/**
 * To list objects currently in Storage
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * Returns string representation of current taskList from Storage.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String result of output from successful execution of Command
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.displayUserInput(ui.formatTaskList(storage.getTaskList()));
    }
}

package karen.command;

import karen.Storage;
import karen.Ui;

/**
 * To list objects currently in Storage
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.displayUserInput(ui.formatTaskList(storage.getTaskList()));
    }
}

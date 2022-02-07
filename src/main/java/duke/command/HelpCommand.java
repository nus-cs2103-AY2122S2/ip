package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Help has arrived!";
    }

    /**
     * Returns false for non-Exit Commands
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        return false;
    }
}

package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        ui.endChat();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

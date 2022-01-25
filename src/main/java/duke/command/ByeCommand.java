package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) {
        ui.endChat();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

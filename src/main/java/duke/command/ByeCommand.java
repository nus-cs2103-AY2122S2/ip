package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForBye();
    }
}

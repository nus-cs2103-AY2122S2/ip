package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ByeCommand implements Command {
    public ByeCommand() {}

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        return true;
    }
}

package Commands;

import Tasks.TaskList;
import util.Storage;
import util.Ui;

public class ByeCommand extends DukeCommand{
    public ByeCommand(String description) {
        super(description);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

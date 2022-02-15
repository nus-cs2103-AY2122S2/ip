package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("Exit");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        response.append(ui.goodbye());
        assert response.length() > 0; // response should not be empty
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

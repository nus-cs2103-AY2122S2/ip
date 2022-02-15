package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        response.append(ui.list(tasks.list()));
        assert response.length() > 0; // response should not be empty
    }
}

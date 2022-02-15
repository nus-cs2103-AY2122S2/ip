package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super("unknown");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        response.append("Sorry, I don't understand that command :/");
        assert response.length() > 0; // response should not be empty
    }
}

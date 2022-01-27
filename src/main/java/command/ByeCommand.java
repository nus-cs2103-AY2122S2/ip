package command;

import app.MickeyException;
import app.Storage;
import app.Ui;
import task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        storage.save(tasks);
        ui.showBye();
    }
}

package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ErrorCommand extends Command {
    private final String err;
    public ErrorCommand(String err) {
        super();
        this.err = err;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(err);
    }
}

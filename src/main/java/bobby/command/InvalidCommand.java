package bobby.command;

import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidMessage();
    }
}

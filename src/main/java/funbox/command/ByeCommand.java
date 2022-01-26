package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;


public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }
}

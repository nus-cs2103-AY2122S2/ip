package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodBye();
        System.exit(0);
    }
}

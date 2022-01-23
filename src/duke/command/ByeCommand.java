package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(Keyword.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

}

package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) {
        ui.byeUser();
    }
}

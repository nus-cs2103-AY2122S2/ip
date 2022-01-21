package duke.commands;

import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

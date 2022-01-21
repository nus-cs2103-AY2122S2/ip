package duke.commands;

import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        ui.showCalendar(calendar);
    }
}

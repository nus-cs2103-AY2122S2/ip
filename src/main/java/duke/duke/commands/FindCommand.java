package duke.commands;

import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        ui.showCalendar(calendar.tasksMatchingKeyword(this.keyword));
    }
}

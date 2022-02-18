package duke.commands;

import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * @param keyword - the keyword to be used for Find
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the calendar with all the tasks that match the given keyword
     * Using the tasksMatchingKeyword method in the Calendar class, obtains
     * a calendar of all tasks matching the keyword. Then, this resultant
     * calendar is then displayed using the ui handler specified.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     */
    @Override
    public String execute(Calendar calendar, Ui ui, Storage storage) {
        return ui.showCalendar(calendar.tasksMatchingKeyword(this.keyword));
    }
}

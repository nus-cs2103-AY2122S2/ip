package duke.commands;

import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Constructs a ListCommand
     */
    public ListCommand() {}

    /**
     * Displays the string representation of the specified calendar using the
     * specified ui handler.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     */
    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        ui.showCalendar(calendar);
    }
}

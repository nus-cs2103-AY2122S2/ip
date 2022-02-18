package duke.commands;

import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.ui.Ui;


public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand
     */
    public ExitCommand() {}

    /**
     * Shows the goodbye message on the ui handler.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     */
    @Override
    public String execute(Calendar calendar, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}

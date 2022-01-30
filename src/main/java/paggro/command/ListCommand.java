package paggro.command;

import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.ui.Ui;

/**
 * This class encapsulates a list command which lists out the tasks in a list.
 */
public class ListCommand extends Command {
    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Carries out the execution of a list command which lists out the tasks in a list.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     */
    @Override
    public void execute(Lister lister, Ui ui, Storage storage) {
        ui.showList(lister.getTasks());
    }
}

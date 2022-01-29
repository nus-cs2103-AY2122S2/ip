package paggro.command;

import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.ui.Ui;

/**
 * This class encapsulates a bye Command which terminates PaggroBot.
 */
public class ByeCommand extends Command {
    /**
     * Constructor of ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Carries out the execution of the Bye command which terminates PaggroBot.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     */
    @Override
    public void execute(Lister lister, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

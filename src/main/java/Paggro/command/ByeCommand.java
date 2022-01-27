package paggro.command;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public void execute(Lister lister, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

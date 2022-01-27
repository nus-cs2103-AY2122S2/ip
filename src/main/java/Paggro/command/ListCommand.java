package paggro.command;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Lister lister, Ui ui, Storage storage) {
        ui.showList(lister.tasks);
    }
}
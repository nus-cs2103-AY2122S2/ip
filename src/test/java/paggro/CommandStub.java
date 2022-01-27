package paggro;

import paggro.command.Command;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.ui.Ui;

public class CommandStub extends Command{
    public CommandStub() {
        super();
    }

    @Override
    public void execute(Lister lister, Ui ui, Storage storage) {
        return;
    }
}

package karen.command;

import karen.Storage;
import karen.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showGoodbye();
    }

}

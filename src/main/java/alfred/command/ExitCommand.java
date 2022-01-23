package alfred.command;

import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

public class ExitCommand extends Command {

    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

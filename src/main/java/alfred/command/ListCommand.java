package alfred.command;

import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

public class ListCommand extends Command {

    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) {
        String out = "";
        out += "Sir, here are the things you need to do:\n";
        out += storage.listToString();
        ui.sandwichAndPrint(out);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

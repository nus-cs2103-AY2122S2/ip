package alfred.command;

import alfred.command.Command;
import alfred.exceptions.MissingInputException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    private String text;

    public FindCommand(String input) {
        this.text = input.substring(4); // remove "find"
    }

    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws
            MissingInputException {
        // validity check
        if ((this.text.length() < 1) || this.text.split(" ").length == 0) {
            throw new MissingInputException();
        }
        // do
        String out = "Here are the matching tasks, sir:\n";
        out += storage.find(this.text);
        ui.sandwichAndPrint(out);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

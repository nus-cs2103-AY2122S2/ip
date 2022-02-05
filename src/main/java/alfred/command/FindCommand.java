package alfred.command;

import alfred.exceptions.MissingInputException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * Encapsulates the find command.
 */
public class FindCommand extends Command {
    private String text;

    /**
     * Constructs a FindCommand object.
     *
     * @param input String input to be matched with tasks for searching.
     */
    public FindCommand(String input) {
        this.text = input.substring(4); // remove "find"
    }

    /**
     * Executes the find command.
     *
     * @param ui      AlfredUserInterface object used for handling interactions with the user.
     * @param storage AlfredStorage object used to manage internal data state of Alfred.
     * @throws MissingInputException if no valid string found after "find" in input.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws
            MissingInputException {
        String out = this.response(ui, storage);
        ui.sandwichAndPrint(out);
    }

    /**
     * Executes the find command.
     *
     * @param ui      AlfredUserInterface object used for handling interactions with the user.
     * @param storage AlfredStorage object used to manage internal data state of Alfred.
     * @return String output for user.
     * @throws MissingInputException if no valid string found after "find" in input.
     */
    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage) throws
            MissingInputException {
        // validity check
        if ((this.text.length() < 1) || this.text.split(" ").length == 0) {
            throw new MissingInputException();
        }
        // do
        String out = "Here are the matching tasks, sir:\n";
        out += storage.find(this.text);
        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

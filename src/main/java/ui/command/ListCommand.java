package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which prints out a list of item
 * stored by user.
 */
public class ListCommand extends Command {
    /**
     * List of items to print in the command
     */
    private final ArrayList<String> list;

    public ListCommand(String name, ArrayList<String> list) {
        super(name);
        this.list = list;
    }

    @Override
    public boolean execute() {
        ArrayList<String> response = new ArrayList<>();
        // Prepend each list item with its numbering inside list
        for (int i = 0; i < this.list.size(); i++) {
            response.add(String.format("%d. %s", i + 1, this.list.get(i)));
        }
        Command.styledPrint(response);
        return false;
    }
}

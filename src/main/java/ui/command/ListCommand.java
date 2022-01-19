package ui.command;

import task.*;
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
    private final ArrayList<Task> list;

    public ListCommand(String name, String args, ArrayList<Task> list) {
        super(name, args);
        this.list = list;
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        ArrayList<String> response = new ArrayList<>();
        // Prepend each list item with its numbering inside list
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            response.add(String.format("%d.%s", i + 1, task.getDescription()));
        }
        Command.styledPrint(response);
        return false;
    }
}

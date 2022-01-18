package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which adds a given item
 * to a given list.
 */
public class AddItemCommand extends Command {
    /**
     * List of items to add a new item to
     */
    private final ArrayList<String> list;

    /**
     * Item to add to list
     */
    private final String item;

    public AddItemCommand(String name, String item, ArrayList<String> list) {
        super(name);
        this.item = item;
        this.list = list;
    }

    @Override
    public boolean execute() {
        this.list.add(this.item);

        ArrayList<String> response = new ArrayList<>();
        response.add(String.format("added: %s", this.item));
        Command.styledPrint(response);
        return false;
    }
}

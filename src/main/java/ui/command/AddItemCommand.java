package ui.command;

import task.*;
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
    private final ArrayList<Task> list;

    public AddItemCommand(String name, String args, ArrayList<Task> list) {
        super(name, args);
        this.list = list;
    }

    @Override
    public boolean execute() {
        // Args for this command represents item to add
        String item = super.getArgs();
        Task task = new Task(item);
        this.list.add(task);

        ArrayList<String> response = new ArrayList<>();
        response.add(String.format("added: %s", item));
        Command.styledPrint(response);
        return false;
    }
}

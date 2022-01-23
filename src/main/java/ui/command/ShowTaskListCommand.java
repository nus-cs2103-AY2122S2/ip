package ui.command;

import task.Task;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which prints out a list of item
 * stored by user.
 */
public class ShowTaskListCommand extends TaskCommand {

    public ShowTaskListCommand(String name, String args, ArrayList<Task> list, File dataFile) {
        super(name, args, list, dataFile);
    }

    @Override
    public boolean execute() throws IllegalArgumentException {
        ArrayList<Task> taskList = this.getTaskList();
        ArrayList<String> response = new ArrayList<>();
        // Prepend each list item with its numbering inside list
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            response.add(String.format("%d.%s", i + 1, task.getDescription()));
        }
        Command.styledPrint(response);
        return false;
    }
}

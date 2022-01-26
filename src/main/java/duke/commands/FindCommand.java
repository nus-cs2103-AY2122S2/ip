package duke.commands;

import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents a find command recognized by the parser.
 * FindCommand object stores a String that is to be used for searching. Upon
 * execution of the object, it will attempt to search for tasks in the task list
 * with description matching that of searchString.
 */
public class FindCommand extends Command {

    protected String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) {
        io.showMessage("Here are the matching tasks in your list:");
        ArrayList<String> tasksString = taskList.search(searchString);
        for (int i = 0; i < tasksString.size(); i++) {
            io.showMessage(i + 1 + ". " + tasksString.get(i));
        }
    }
}

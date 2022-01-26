package duke.commands;

import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;

import java.util.ArrayList;

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

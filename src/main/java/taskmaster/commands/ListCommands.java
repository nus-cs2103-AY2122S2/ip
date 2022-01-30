package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

public class ListCommands extends Commands {
    public ListCommands() {
        super("");
    }

    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions {
        return tasklist.list();
    }
}

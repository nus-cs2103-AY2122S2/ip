package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.TaskList;
import taskmaster.util.Storage;
import taskmaster.userinterface.UserInterface;

public class ByeCommands extends Commands {
    public ByeCommands() {
        super("");
    }

    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions {
        return ui.displayByeMessage();
    }
}

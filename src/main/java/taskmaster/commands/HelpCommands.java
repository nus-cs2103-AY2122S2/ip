package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.TaskList;
import taskmaster.util.Storage;
import taskmaster.userinterface.UserInterface;

public class HelpCommands extends Commands {
    public HelpCommands() {
        super("");
    }

    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions {
        return ui.displayListOfCommand();
    }
}

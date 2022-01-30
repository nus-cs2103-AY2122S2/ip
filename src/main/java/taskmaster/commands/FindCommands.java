package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.TaskList;
import taskmaster.util.Storage;
import taskmaster.userinterface.UserInterface;

public class FindCommands extends Commands {
    private String input;

    public FindCommands(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions {
        String[] stringIntoParts = input.split(" ");
        if (stringIntoParts.length == 1) {
            throw new DukeExceptions("ERROR: find command requires a parameter to specify"
                    + " what keyword to find");
        }
        String toFind = input.substring(input.indexOf(" ") + 1);
        return tasklist.find(toFind);
    }
}

package taskmaster.commands;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;


/**
 * This class inherits from the Command class.
 * It encapsulates Commands that display
 * the list of commands available in the program.
 */
public class HelpCommands extends Commands {
    /**
     * The constructor for HelpCommands.
     */
    public HelpCommands() {
        super("");
    }
    /**
     * Execute the command.
     *
     * @param ui The User interface.
     *
     * @param storage The file that is storing the task information.
     *
     * @return Returns a string confirmation that the task has been executed.
     *
     * @throws TaskmasterExceptions Throws an exception if execution fails.
     */
    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws TaskmasterExceptions {
        return ui.displayListOfCommand();
    }
}

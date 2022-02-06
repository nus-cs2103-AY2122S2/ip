package taskmaster.commands;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/**
 * This class inherits from the Command class.
 * It encapsulates Commands that the list of task
 * that matches the specified keyword.
 */
public class FindCommands extends Commands {
    /** The user input. **/
    private String input;

    /**
     * Constructor for FindCommands.
     * @param input The user input.
     */
    public FindCommands(String input) {
        super(input);
        this.input = input;
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
        String[] stringIntoParts = input.split(" ");
        if (stringIntoParts.length == 1) {
            throw new TaskmasterExceptions("ERROR: find command requires a parameter to specify"
                    + " what keyword to find");
        }
        String toFind = input.substring(input.indexOf(" ") + 1);
        return tasklist.find(toFind);
    }
}

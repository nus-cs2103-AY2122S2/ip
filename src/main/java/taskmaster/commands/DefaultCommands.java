package taskmaster.commands;
import taskmaster.exception.TaskmasterExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/**
 * This class inherits from the Command class.
 * It encapsulates Commands that display
 * the default reply from taskmaster.
 */
public class DefaultCommands extends Commands {
    /** The user input. **/
    private String userInput;

    /**
     * The constructor for DefaultCommands.
     * @param userInput The user input.
     */
    public DefaultCommands(String userInput) {
        super(userInput);
        this.userInput = userInput;
    }
    /**
     * Execute the command.
     * @param tasklist The task list that contains the task.
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
        return ui.displayInvalidCommand(this.userInput);
    }
}

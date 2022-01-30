import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.TaskList;
import taskmaster.util.Storage;
import taskmaster.commands.Commands;

/**
 * This class encapsulates the main program, Taskmaster.
 */

public class Taskmaster {
    /** The user interface that allows the user to communicate with Taskmaster. **/
    protected UserInterface ui;
    protected TaskList taskList;
    protected Storage storage;

    /**
     * Constructor for Taskmaster the chatbot.
     */

    private Taskmaster () {
        this.ui = new UserInterface();
    }

    public String getResponse(String input) {
        try {
            Commands command = ui.performCommand(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeExceptions e) {
            return ui.displayErrorMessage(e.getMessage());
        }
    }
}

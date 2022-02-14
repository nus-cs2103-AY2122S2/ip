package duke.command;

import duke.ui.TaskList;
import duke.ui.Ui;
import duke.ui.Storage;

/**
 * An extension from the Command class.
 * Deals with the termination of the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns a string indicating the temrminating of the program.
     * Executes a farewell print statement.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Returns true for ExitCommands
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

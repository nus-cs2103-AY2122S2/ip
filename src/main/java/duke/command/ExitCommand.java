package java.duke.command;

import java.duke.ui.TaskList;
import java.duke.ui.Ui;
import java.duke.ui.Storage;

public class ExitCommand extends Command {

    /**
     * Executes a farewell print statement.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
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

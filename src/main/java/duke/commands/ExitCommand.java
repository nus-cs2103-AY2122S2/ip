package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;

/**
 * ExitCommand is a Command that terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Checks if this is an exit command, and only returns yes for an
     * exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints a farewell message to the user and exits the program.
     * @param tasks   task list local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showGoodByeMessage();
    }
}

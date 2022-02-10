package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }
}

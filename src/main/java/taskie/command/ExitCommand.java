package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;


/**
 * A class that specifies the behavior of a command that exits the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("Exit");
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     * @param response StringBuilder object to append results to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        response.append(ui.goodbye());
        assert response.length() > 0; // response should not be empty
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

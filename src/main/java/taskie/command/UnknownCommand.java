package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

/**
 * A class that specifies the behavior of an unknown command.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super("unknown");
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
        response.append("Sorry, I don't understand that command :/");
        assert response.length() > 0; // response should not be empty
    }
}

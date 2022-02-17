package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

/**
 * A class that specifies the behavior of a command that list all the task in a task list.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list");
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
        response.append(ui.list(tasks.list()));
        assert response.length() > 0; // response should not be empty
    }
}

package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Help.
 * Supports the Command feature of displaying the list of commands.
 */
public class HelpCommand extends Command {
    /**
     * Displays the list of commands.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.response = new Response(ui.getCommands());
    }
}

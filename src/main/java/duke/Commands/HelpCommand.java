package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Tasks.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.io.IOException;

/**
 * HelpCommand is a subclass of DukeCommand that is created when the user inputs "help"
 */
public class HelpCommand extends DukeCommand {
    public HelpCommand() {
        super("help");
    }

    /**
     * Generates a list of commands that the user can execute
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     * @return String of commands
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return ui.showCommandList();
    }
}

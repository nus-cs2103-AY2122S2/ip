package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

/**
 * The CommandUnmark class contains the basic
 * behaviour of a List Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandList extends Command {
    /**
     * Overrides method in parent class.
     * This method models the execution of a List Command.
     * The TaskList returns a single String of all the consolidated
     * tasks and the Ui prints it.
     *
     * @param tasks - to obtain a String of all the consolidated tasks
     * @param ui - to print all the tasks
     * @param storage - not used
     * @throws DukeException - thrown if TaskList does not contain tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks.tasksAsString());
    }
}

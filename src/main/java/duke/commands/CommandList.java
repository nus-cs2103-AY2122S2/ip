package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.showList(tasks.tasksAsString());
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}

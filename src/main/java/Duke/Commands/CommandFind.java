package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

/**
 * The CommandFind class contains basic attributes and
 * behaviours of a Find Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandFind extends Command {
    /**
     * Keyword used for the search
     */
    private final String keyword;

    /**
     * Sole constructor.
     *
     * @param keyword - raw task number input from the user
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of a Find Command.
     * The TaskList searches for the tasks with the keyword in their name,
     * and returns all the tasks in a single consolidated String.
     * The Ui then prints the resulting String.
     *
     * @param tasks - to search TaskList for tasks with names that
     *                contain the keyword
     * @param ui - to print all the tasks that contains the keyword
     * @param storage - not used
     * @throws DukeException - thrown if TaskList does not contain tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showResult(tasks.find(this.keyword));
    }
}

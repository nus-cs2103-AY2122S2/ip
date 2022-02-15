package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The UnmarkCommand class contains basic attributes and
 * behaviours of a unmark command.
 *
 * @author  Melvin Chan Zijun
 */
public class UnmarkCommand extends Command {
    /**
     * Index of Task to be unmarked
     */
    private final int taskNum;

    /**
     * Sole constructor.
     * Possible NumberFormatException during the parsing of
     * the input from String into int. Checks whether
     * the String input can be parsed into an int.
     *
     * @param str - raw task number input from the user
     * @throws DukeException - str cannot be parsed into an int
     */
    public UnmarkCommand(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Overrides method in parent class.
     * Executes the unmark command, saves the data and returns
     * a message to let user know that execution was
     * successful.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(this.taskNum);
            storage.save(tasks);
            return ui.showTaskUnmarked();
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}

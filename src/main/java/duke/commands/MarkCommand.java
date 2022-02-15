package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The MarkCommand class contains basic attributes and
 * behaviours of a mark command.
 *
 * @author  Melvin Chan Zijun
 */
public class MarkCommand extends Command {
    /**
     * Index of Task
     */
    private final int taskNum;

    /**
     * Sole constructor.
     * Possible NumberFormatException during the parsing of
     * the input from String into int. Checks whether
     * the String input can be parsed into an int.
     *
     * @param str raw task number input from the user
     * @throws DukeException if str is not an int
     */
    public MarkCommand(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Overrides method in parent class.
     * Executes the mark command, saves the data and returns
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
            tasks.mark(this.taskNum);
            storage.save(tasks);
            return ui.showTaskMarked();
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}

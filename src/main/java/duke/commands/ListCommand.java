package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The ListCommand class contains the basic
 * behaviour of a list command.
 *
 * @author  Melvin Chan Zijun
 */
public class ListCommand extends Command {
    /**
     * Overrides method in parent class.
     * Executes the exit command, and returns list
     * of tasks in task list
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String message of successful execution
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

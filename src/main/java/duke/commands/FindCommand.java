package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The FindCommand class contains basic attributes and
 * behaviours of a find command.
 *
 * @author  Melvin Chan Zijun
 */
public class FindCommand extends Command {
    /**
     * Keyword used for the search
     */
    private final String keyword;

    /**
     * Sole constructor.
     *
     * @param keyword - raw task number input from the user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Overrides method in parent class.
     * Executes the find command, returns the result
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String search result
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.showResult(tasks.find(this.keyword));
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}

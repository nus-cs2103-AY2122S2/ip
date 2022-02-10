package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The Find Command
 */
public class FindCommand extends Command {

    private String search;

    @Override
    public void input(String inst) throws DukeException {
        search = checkExist(inst);
    }

    /**
     * Finds and returns all similar tasks in the list
     *
     * @param tasks The task list in question
     * @return A string representation of the list of tasks found
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.find(search);
    }
}

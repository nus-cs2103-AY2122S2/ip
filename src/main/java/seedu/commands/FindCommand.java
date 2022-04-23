package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The Find Command
 */
public class FindCommand extends Command {

    private String search;

    /**
     * Checks whether the instruction is empty
     *
     * @param inst The user input
     * @throws DukeException There was no input
     */
    @Override
    public void validate(String inst) throws DukeException {
        search = checkExist(inst);
    }

    /**
     * Finds all the tasks with the search term from the task list
     *
     * @param tasks The list of tasks
     * @return A list of tasks with the search term found from the task list in string representation
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.find(search);
    }
}

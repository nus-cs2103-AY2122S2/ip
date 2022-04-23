package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Delete Command
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Checks whether an integer was given, and it is bigger than 0
     * Converts it to be used as an index for the task list
     *
     * @param inst The user input
     * @throws DukeException The user input was not an integer, or it is bigger than 0
     */
    @Override
    public void validate(String inst) throws DukeException {
        idx = super.checkInt(inst) - INDEX_OFFSET;
    }

    /**
     * Deletes the task from the list
     *
     * @param tasks The task list
     * @return The deleted task
     * @throws DukeException Task index could not be found
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.remove(idx);
        return super.show("Deleted Task:", task);
    }
}

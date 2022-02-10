package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Delete Command
 */
public class DeleteCommand extends Command {

    private int idx;

    @Override
    public void input(String inst) throws DukeException {
        idx = super.checkIdx(inst);
    }

    /**
     * Deletes the task from the task list
     *
     * @param tasks The task list in question
     * @return A string specifying which task is deleted
     * @throws DukeException Task index cannot be found in the list
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.remove(idx);
        return "Task " + task.toString() + " is deleted!";
    }
}

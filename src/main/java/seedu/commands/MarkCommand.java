package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Mark Command
 */
public class MarkCommand extends Command {

    private int idx;

    @Override
    public void input(String inst) throws DukeException {
        idx = super.checkIdx(inst);
    }

    /**
     * Finds and marks the task in the task list as completed
     *
     * @param tasks The task list in question
     * @return The task to be marked
     * @throws DukeException Throws if task is not found
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        task.mark();
        return "Task: " + task.toString() + " is marked!";
    }
}

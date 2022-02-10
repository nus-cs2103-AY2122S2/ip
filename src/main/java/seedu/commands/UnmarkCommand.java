package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Unmark Command
 */
public class UnmarkCommand extends Command {

    private int idx;

    @Override
    public void input(String inst) throws DukeException {
        idx = super.checkIdx(inst);
    }

    /**
     * Finds and unmarks the task in the task list as not completed
     *
     * @param tasks The task list in question
     * @return The task to be unmarked
     * @throws DukeException Throws if task is not found
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        assert task != null: "Need to have task before continuing";
        task.unmark();
        return "Task: " + task.toString() + " is unmarked!";
    }
}

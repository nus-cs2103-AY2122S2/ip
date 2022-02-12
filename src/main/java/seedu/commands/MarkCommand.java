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
        idx = super.checkInt(inst) - 1;
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
        assert task != null: "Need to have task before continuing";
        task.mark();
        return "Task: " + task.toString() + " is marked!";
    }
}

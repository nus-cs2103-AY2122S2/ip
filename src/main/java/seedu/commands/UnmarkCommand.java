package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

public class UnmarkCommand extends Command {

    private int idx;

    @Override
    public void input(String inst) throws DukeException {
        idx = super.checkIdx(inst);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        task.unmark();
        return "Task: " + task.toString() + " is unmarked!";
    }
}

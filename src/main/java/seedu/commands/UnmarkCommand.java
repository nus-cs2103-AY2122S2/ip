package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

public class UnmarkCommand extends Command {

    private int idx;

    @Override
    public void validate(String inst) throws DukeException {
        idx = super.checkInt(inst) - INDEX_OFFSET;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        assert task != null: "Need to have task before continuing";
        task.unmark();
        return super.print("Unmark:", task);
    }
}

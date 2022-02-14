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
    public void validate(String inst) throws DukeException {
        idx = super.checkInt(inst) - INDEX_OFFSET;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.remove(idx);
        return super.print("Deleted Task:", task);
    }
}

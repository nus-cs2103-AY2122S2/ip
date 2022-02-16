package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Mark Command
 */
public class MarkCommand extends Command {

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
     * Finds and marks the task in the task list as completed
     *
     * @param tasks The task list in question
     * @return The task to be marked
     * @throws DukeException Task index could not be found
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        assert task != null: "Need to have task before continuing";
        task.mark();
        return super.show("Marked:", task);
    }
}

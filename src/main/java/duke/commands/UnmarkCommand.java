package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

/**
 * Command that unmarks a Task from TaskList
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index index of Task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks Task from TaskList.
     *
     * @return Message for completing the command which is displayed to user.
     * @throws DukeInvalidArgumentException If index is not within TaskList.
     */
    @Override
    public String execute() throws DukeInvalidArgumentException {
        if (index > taskList.size() - 1 || index < 0) {
            throw new DukeInvalidArgumentException("I am afraid that's an invalid task!" +
                    " Please check your task number");
        }
        Task unmarkedTask = taskList.unmarkTask(index);
        return String.format("Very well. The following task has been marked as not done:" +
                "%s", unmarkedTask);
    }
}

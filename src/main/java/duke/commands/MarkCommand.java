package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

/**
 * Command that marks Task from TaskList as done.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index index of Task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks Task from TaskList as done.
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
        Task markedTask = taskList.markTask(index);
        return String.format("Duly noted. The following task has been marked as done:\n" +
                "%s", markedTask);
    }
}

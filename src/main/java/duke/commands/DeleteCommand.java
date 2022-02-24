package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

/**
 * Command that deletes a Task from TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index index of Task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes Task from TaskList
     *
     * @return Message for completing the command which is displayed to user.
     * @throws DukeInvalidArgumentException If index is not within TaskList.
     */
    @Override
    public String execute() throws DukeInvalidArgumentException {
        if (index > taskList.size() - 1 || index < 0) {
            throw new DukeInvalidArgumentException("I am afraid that's an invalid task!" +
                                               "Please check your task number");
        }
        Task deletedTask = taskList.deleteTask(index);
        return String.format("As you wish. The following task has been removed:\n" +
                "%s\n" + "You now have %d item(s) in your list", deletedTask, taskList.size());
    }
}

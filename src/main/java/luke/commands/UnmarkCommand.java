package luke.commands;

import luke.data.TaskList;

/**
 * Implements the unmark command.
 */
public class UnmarkCommand extends UpdateCommand {

    private static final String DEFAULT_MESSAGE = "Force should be used for greater good!\nI've forced this task as not done yet...";

    /**
     * Constructs the unmark command with the specified index.
     * @param index The specified index of the task to be mark as not done in the task list.
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    /**
     * Takes in a task list, marks the task at the specified index in the task list as not done.
     * Returns its command result.
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.get(getIndex() - 1).unmarkAsDone();
            return new CommandResult(DEFAULT_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("The force cannot find the task.\nPlease try again :(");
        }
    }
}

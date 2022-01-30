package luke.commands;

import luke.data.TaskList;
import luke.data.tasks.Task;

/**
 * Implements the delete command.
 */
public class DeleteCommand extends UpdateCommand {

    private static final String DEFAULT_MESSAGE = "Forcing it out... Success! I've removed the following task:\n\t%s";

    /**
     * Constructs the delete command with the specified index.
     * @param index The specified index of the task to be deleted in the task list.
     */
    public DeleteCommand(int index) {
        super(index);
    }


    @Override
    /**
     * Takes in a task list, remove the specified index from the task list and returns its command result.
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    public CommandResult execute(TaskList taskList) {
        try {
            Task removedTask = taskList.remove(getIndex() - 1);
            return new CommandResult(String.format(DEFAULT_MESSAGE, removedTask));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("The force cannot find the task.\nPlease try again :(");
        }
    }
}

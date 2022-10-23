package luke.commands;

import luke.data.TaskList;

/**
 * Implements the list command.
 */
public class ListCommand extends ReadCommand {

    private static final String NO_ITEM_MESSAGE = "Yay! You have no task to do!";
    private static final String START_ITEM_MESSAGE = "Currently, you have the following tasks:\n";

    /**
     * Takes in a task list and read task list.
     *
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        return readTaskList(taskList, NO_ITEM_MESSAGE, START_ITEM_MESSAGE);
    }
}

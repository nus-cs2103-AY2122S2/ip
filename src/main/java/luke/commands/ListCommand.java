package luke.commands;

import luke.data.TaskList;

public class ListCommand extends ReadCommand {

    public static final CommandAction COMMAND_ACTION = CommandAction.LIST;
    private static final String NO_ITEM_MESSAGE = "Yay! You have no task to do!";
    private static final String START_ITEM_MESSAGE = "Currently, you have the following tasks:\n";

    @Override
    public CommandResult execute(TaskList taskList) {
        return readTaskList(taskList, NO_ITEM_MESSAGE, START_ITEM_MESSAGE);
    }
}

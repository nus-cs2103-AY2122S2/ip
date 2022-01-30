package luke.commands;

import luke.data.TaskList;

/**
 * Implements the find command.
 */
public class FindCommand extends ReadCommand {

    private static final String NO_ITEM_MESSAGE = "The force is unable to find any task with the keyword...\n"
            + "The keyword parsed is \"%s\".";
    private static final String START_ITEM_MESSAGE = "The force found the following matching tasks:\n";
    private String keyword;

    /**
     * Constructs the find command with the specified keyword.
     *
     * @param keyword The specified keyword to filter the task list by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Takes in a task list, filter the task list based on keywords and read the task list.
     *
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String emptyMsg = String.format(NO_ITEM_MESSAGE, keyword);
        taskList.setFiltered(x -> x.getCommandString().toLowerCase().contains(keyword));
        return readTaskList(taskList, emptyMsg, START_ITEM_MESSAGE, true);
    }
}

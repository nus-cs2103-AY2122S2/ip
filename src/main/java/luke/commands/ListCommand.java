package luke.commands;

import luke.data.TaskList;

/**
 * Implements the list command.
 */
public class ListCommand extends Command {

    /**
     * Takes in a task list, loop through each task and add them to the message string.
     * Returns its command result.
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String msg = "Yay! You have no task to do!";
        if (!taskList.isEmpty()) {
            msg = "Currently, you have the following tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                msg += String.format("\t%d. %s\n", i + 1, taskList.get(i));
            }
            msg = msg.stripTrailing();
        }
        return new CommandResult(msg);
    }
}

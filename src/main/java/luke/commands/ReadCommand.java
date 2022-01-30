package luke.commands;

import luke.data.TaskList;

/**
 * Framework for commands that reads the task list.
 */
public abstract class ReadCommand extends Command {

    /**
     * Takes in a task list and return the appropriate message based on the items in the task.
     *
     * @param taskList The task list to loop through.
     * @param emptyMsg The message to print if the task list is empty.
     * @param startMsg The message to prefix the task list with if the task list is not empty.
     * @return The command result of the items in the task list.
     */
    public CommandResult readTaskList(TaskList taskList, String emptyMsg, String startMsg) {
        return readTaskList(taskList, emptyMsg, startMsg, false);
    }

    /**
     * Takes in a task list and return the appropriate message based on the items in the task.
     *
     * @param taskList   The task list to loop through.
     * @param emptyMsg   The message to print if the task list is empty.
     * @param startMsg   The message to prefix the task list with if the task list is not empty.
     * @param isFiltered To toggle whether the method should filter away any task or read all tasks in the list.
     * @return The command result of the items in the task list.
     */
    public CommandResult readTaskList(TaskList taskList, String emptyMsg, String startMsg, boolean isFiltered) {
        String msg = emptyMsg;
        boolean isEmpty = true;
        if (!taskList.isEmpty()) {
            msg = startMsg;
            for (int i = 0; i < taskList.size(); i++) {
                if (!isFiltered || taskList.get(i).isNotFiltered()) {
                    msg += String.format("\t%d. %s\n", i + 1, taskList.get(i));
                    isEmpty = false;
                }
            }
            msg = msg.stripTrailing();
        }
        msg = isEmpty ? emptyMsg : msg;
        return new CommandResult(msg);
    }
}

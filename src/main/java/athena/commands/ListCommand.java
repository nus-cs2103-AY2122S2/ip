package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a list command given to Athena by the user. When executed, returns the contents of the
 * current task list.
 */
public class ListCommand extends Command {

    /**
     * Returns the String representation of the given task list.
     *
     * @param taskList TaskList instance to be displayed.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        return Messages.getTaskListDialog(taskList);
    }
}

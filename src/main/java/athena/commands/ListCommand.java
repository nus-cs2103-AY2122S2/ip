package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a list command given to Athena by the user. When executed, displays the
 * current task list to the ui.
 */
public class ListCommand extends Command {

    /**
     * Displays the given task list to the ui.
     *
     * @param taskList TaskList instance to be displayed.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        return Messages.getTaskListDialog(taskList);
    }
}

package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

/**
 * Represents a list command given to Athena by the user. When executed, displays the
 * current task list to the ui.
 */
public class ListCommand extends Command {

    /**
     * Displays the given task list to the ui.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList TaskList instance to be displayed.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showTaskList();
    }
}
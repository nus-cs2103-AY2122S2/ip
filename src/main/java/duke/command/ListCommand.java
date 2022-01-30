package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Encapsulates a command to show the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays a given task list.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

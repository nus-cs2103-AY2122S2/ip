package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed clears all tasks in the task list.
 */
public class ClearTaskCommand extends Command {
    /**
     * Invokes <code>clear</code> method of <code>taskList</code> to clear all tasks.
     * After that, prompts <code>ui</code> to display response messages to user.
     *      
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.clear();
        ui.showMessage("All tasks cleared.");
    }
}

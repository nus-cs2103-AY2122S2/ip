package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed displays to the user all tasks in the task list.
 */
public class ListTaskCommand extends Command {
    /**
     * Gets all tasks from <code>taskList</code>, then prompts <code>ui</code> to display them.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        assert taskList != null;
        assert ui != null;
        int numberOfTasks = taskList.getNumberOfTasks();

        if (numberOfTasks == 0) {
            ui.showMessage("Congratulations! There are no tasks in your list :)");
            return;
        }

        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            ui.showMessage(String.format("%d. %s", i + 1, taskList.getDescriptionOfTaskAtIndex(i)));
        }
    }
}

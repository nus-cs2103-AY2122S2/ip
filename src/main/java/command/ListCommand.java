package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a type of Command - List.
 * Displays all the task information.
 */
public class ListCommand extends Command {

    /**
     * Displays all the task information.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksCountMessage(tasks);
        ui.showListOfTasksMessage(tasks);
    }
}

package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents list command
 */
public class ListCommand extends Command {

    /**
     * Displays list of tasks
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }
}

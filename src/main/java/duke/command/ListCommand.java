package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} object.
     */
    public ListCommand() {}

    /**
     * Asks the UI to display the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showMessage("There are no tasks in your list~");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            ui.showList(tasks);
        }
    }

}

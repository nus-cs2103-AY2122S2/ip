package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} object with keyword LIST.
     */
    public ListCommand() {
        super(Keyword.LIST);
    }

    /**
     * Asks the UI to show the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

}

package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {

    /**
     * List out all the tasks in the list.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond(tasks.listing());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}

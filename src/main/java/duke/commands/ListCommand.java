package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * List out all the tasks in the list.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.listing();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
